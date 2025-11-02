# About
This project contains a simple API to test the dynamo db 
transactional operations.

Given that Spring doesn't support transactional operations with DynamoDB out of the box,
this project uses the AWS SDK for Java v2 to implement these operations using a class
that can take a list of entities and save them on the database in a single transaction.