#!/bin/bash
awslocal s3 mb s3://my-test-bucket
awslocal s3 cp /tmp/mock_data.txt s3://my-test-bucket/input/data.txt
