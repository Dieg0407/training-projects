#!/bin/bash

# DynamoDB Local endpoint
ENDPOINT="http://dynamodb-local:8000"

echo "Iniciando creaci├│n de tablas en DynamoDB Local..."

# Funci├│n para verificar si una tabla existe
table_exists() {
    local table_name=$1
    aws dynamodb describe-table --table-name "$table_name" --endpoint-url $ENDPOINT --region us-east-1 >/dev/null 2>&1
    return $?
}

# Crear tabla MonthlyBalance
echo "Creando tabla MonthlyBalance..."
if table_exists "MonthlyBalance"; then
    echo "La tabla MonthlyBalance ya existe"
else
    aws dynamodb create-table \
        --table-name MonthlyBalance \
        --attribute-definitions \
            AttributeName=account_id,AttributeType=S \
            AttributeName=year_month,AttributeType=S \
        --key-schema \
            AttributeName=account_id,KeyType=HASH \
            AttributeName=year_month,KeyType=RANGE \
        --billing-mode PAY_PER_REQUEST \
        --endpoint-url $ENDPOINT \
        --region us-east-1

    if [ $? -eq 0 ]; then
        echo "Ô£à Tabla MonthlyBalance creada exitosamente"
    else
        echo "ÔØî Error al crear la tabla MonthlyBalance"
        exit 1
    fi
fi

# Crear tabla TransactionEvent
echo "Creando tabla TransactionEvent..."
if table_exists "TransactionEvent"; then
    echo "La tabla TransactionEvent ya existe"
else
    aws dynamodb create-table \
        --table-name TransactionEvent \
        --attribute-definitions \
            AttributeName=transaction_id,AttributeType=S \
            AttributeName=timestamp,AttributeType=S \
        --key-schema \
            AttributeName=transaction_id,KeyType=HASH \
            AttributeName=timestamp,KeyType=RANGE \
        --billing-mode PAY_PER_REQUEST \
        --endpoint-url $ENDPOINT \
        --region us-east-1

    if [ $? -eq 0 ]; then
        echo "Ô£à Tabla TransactionEvent creada exitosamente"
    else
        echo "ÔØî Error al crear la tabla TransactionEvent"
        exit 1
    fi
fi

# Esperar a que las tablas est├®n activas
echo "Esperando que las tablas est├®n activas..."
sleep 5

# Verificar estado de las tablas
echo "Verificando estado de las tablas..."
aws dynamodb list-tables --endpoint-url $ENDPOINT --region us-east-1

echo "­ƒÄë Inicializaci├│n de tablas completada!"