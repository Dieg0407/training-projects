#!/bin/bash

# DynamoDB Local endpoint
ENDPOINT="http://dynamodb-local:8000"

echo "Insertando datos de ejemplo en las tablas..."

# Insertar datos de ejemplo en MonthlyBalance
echo "Insertando datos de ejemplo en MonthlyBalance..."

aws dynamodb put-item \
    --table-name MonthlyBalance \
    --item '{
        "account_id": {"S": "ACC001"},
        "year_month": {"S": "202401"},
        "balance": {"N": "1500.50"},
        "currency": {"S": "USD"},
        "last_updated": {"S": "2024-01-31T23:59:59Z"}
    }' \
    --endpoint-url $ENDPOINT \
    --region us-east-1

aws dynamodb put-item \
    --table-name MonthlyBalance \
    --item '{
        "account_id": {"S": "ACC001"},
        "year_month": {"S": "202402"},
        "balance": {"N": "2250.75"},
        "currency": {"S": "USD"},
        "last_updated": {"S": "2024-02-29T23:59:59Z"}
    }' \
    --endpoint-url $ENDPOINT \
    --region us-east-1

aws dynamodb put-item \
    --table-name MonthlyBalance \
    --item '{
        "account_id": {"S": "ACC002"},
        "year_month": {"S": "202401"},
        "balance": {"N": "3500.00"},
        "currency": {"S": "USD"},
        "last_updated": {"S": "2024-01-31T23:59:59Z"}
    }' \
    --endpoint-url $ENDPOINT \
    --region us-east-1

echo "Ô£à Datos insertados en MonthlyBalance"

# Insertar datos de ejemplo en TransactionEvent
echo "Insertando datos de ejemplo en TransactionEvent..."

aws dynamodb put-item \
    --table-name TransactionEvent \
    --item '{
        "transaction_id": {"S": "TXN001"},
        "timestamp": {"S": "2024-01-15T10:30:00Z"},
        "account_id": {"S": "ACC001"},
        "amount": {"N": "250.00"},
        "transaction_type": {"S": "CREDIT"},
        "description": {"S": "Salary deposit"},
        "status": {"S": "COMPLETED"}
    }' \
    --endpoint-url $ENDPOINT \
    --region us-east-1

aws dynamodb put-item \
    --table-name TransactionEvent \
    --item '{
        "transaction_id": {"S": "TXN002"},
        "timestamp": {"S": "2024-01-15T14:45:00Z"},
        "account_id": {"S": "ACC001"},
        "amount": {"N": "-50.00"},
        "transaction_type": {"S": "DEBIT"},
        "description": {"S": "ATM withdrawal"},
        "status": {"S": "COMPLETED"}
    }' \
    --endpoint-url $ENDPOINT \
    --region us-east-1

aws dynamodb put-item \
    --table-name TransactionEvent \
    --item '{
        "transaction_id": {"S": "TXN003"},
        "timestamp": {"S": "2024-01-16T09:15:00Z"},
        "account_id": {"S": "ACC002"},
        "amount": {"N": "1000.00"},
        "transaction_type": {"S": "CREDIT"},
        "description": {"S": "Transfer received"},
        "status": {"S": "COMPLETED"}
    }' \
    --endpoint-url $ENDPOINT \
    --region us-east-1

echo "Ô£à Datos insertados en TransactionEvent"

echo "­ƒÄë Datos de ejemplo insertados correctamente!"

# Mostrar conteo de elementos en cada tabla
echo ""
echo "Conteo de elementos por tabla:"
echo "MonthlyBalance:"
aws dynamodb scan --table-name MonthlyBalance --select "COUNT" --endpoint-url $ENDPOINT --region us-east-1 --output text --query 'Count'

echo "TransactionEvent:"
aws dynamodb scan --table-name TransactionEvent --select "COUNT" --endpoint-url $ENDPOINT --region us-east-1 --output text --query 'Count'