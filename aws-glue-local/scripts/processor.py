import sys
from pyspark.context import SparkContext
from awsglue.context import GlueContext
from awsglue.job import Job
from pyspark.sql.functions import col, substring, length, struct, to_json

# --- LOCAL CONFIGURATION ---
# When running inside Docker, use the service names defined in docker-compose
LOCAL_S3_ENDPOINT = "localhost:4566" 
LOCAL_KAFKA_BOOTSTRAP = "localhost:9092"
INPUT_PATH = "s3a://my-test-bucket/input/data.txt" # Note: use s3a:// for local Spark
TOPIC_NAME = "target-test-topic"

sc = SparkContext()

# Tell Spark to redirect S3 calls to LocalStack
hadoop_conf = sc._jsc.hadoopConfiguration()
hadoop_conf.set("fs.s3a.endpoint", LOCAL_S3_ENDPOINT)
hadoop_conf.set("fs.s3a.access.key", "test")
hadoop_conf.set("fs.s3a.secret.key", "test")
hadoop_conf.set("fs.s3a.path.style.access", "true")
hadoop_conf.set("fs.s3a.connection.ssl.enabled", "false")
hadoop_conf.set("fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")

glueContext = GlueContext(sc)
spark = glueContext.spark_session

# 1. Read from LocalStack S3
raw_df = spark.read.text(INPUT_PATH)

# 2. Filter Header/Footer (120 chars only)
cleaned_df = raw_df.filter(length(col("value")) == 120)

# 3. Parse Fixed-Width Logic
parsed_df = cleaned_df.select(
    substring(col("value"), 1, 1).alias("classification"),
    substring(col("value"), 2, 4).alias("id1"),
    substring(col("value"), 51, 30).alias("owner_name"),
    substring(col("value"), 81, 10).alias("amount")
    # ... add other fields as needed
)

# 4. Transform to JSON for Kafka
kafka_output_df = parsed_df.select(
    to_json(struct([col(c) for c in parsed_df.columns])).alias("value")
)

print(kafka_output_df)

# 5. Write to Local Kafka
# Use standard Spark Kafka write (Glue Kafka sink is for AWS-managed connections)
kafka_output_df.write \
    .format("kafka") \
    .option("kafka.bootstrap.servers", LOCAL_KAFKA_BOOTSTRAP) \
    .option("topic", TOPIC_NAME) \
    .save()

print("Job completed successfully!")
