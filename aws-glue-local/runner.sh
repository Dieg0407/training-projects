#!/bin/bash

# Generate mock data
python3 ./scripts/seed_data.py

# docker compose up
echo "Setting up services..."
docker compose up -d

echo "Waiting for services to go live"
# wait for services to go live
sleep 30

echo "Triggering publishing job"
docker run -it --rm \
    --network=host \
    -v $(pwd):/home/hadoop/workspace/ \
    -e AWS_REGION=us-east-1 \
    -e AWS_ACCESS_KEY_ID=test \
    -e AWS_SECRET_ACCESS_KEY=test \
    public.ecr.aws/glue/aws-glue-libs:5 \
    spark-submit  --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.0.1  /home/hadoop/workspace/scripts/processor.py

echo "Finished!"
echo "Check http://localhost:8080/ui/clusters/local/all-topics/target-test-topic"
