package com.dieg0407.dynamo.transactions.infra

import com.dieg0407.dynamo.transactions.balance.EntityMarker
import com.dieg0407.dynamo.transactions.balance.MonthlyBalance
import com.dieg0407.dynamo.transactions.balance.TransactionEvent
import com.dieg0407.dynamo.transactions.balance.TransactionHandler
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.Put
import software.amazon.awssdk.services.dynamodb.model.ReturnConsumedCapacity
import software.amazon.awssdk.services.dynamodb.model.TransactWriteItem
import software.amazon.awssdk.services.dynamodb.model.TransactWriteItemsRequest

@Component
class DynamoTransactionHandler(
    val dynamoDbClient: DynamoDbClient,
) : TransactionHandler {
    companion object {
        val mappers: Map<Class<out EntityMarker>, EntityToDynamoDbMap<out EntityMarker>> =
            mapOf(
                MonthlyBalance::class.java to MonthlyBalanceToDynamoDbMap(),
                TransactionEvent::class.java to TransactionEventToDynamoDbMap(),
            )
    }

    override fun save(vararg entities: EntityMarker) {
        if (entities.isEmpty()) return

        val updates = mutableListOf<TransactWriteItem>()
        for (entity in entities) {
            val rawMapper =
                mappers[entity::class.java]
                    ?: throw IllegalArgumentException("No mapper found for entity type: ${entity::class.java}")

            @Suppress("UNCHECKED_CAST")
            val mapper = rawMapper as EntityToDynamoDbMap<EntityMarker>
            val itemUpdate = mapper.toDynamoDbMap(entity)
            updates.add(TransactWriteItem.builder().put(itemUpdate).build())
        }

        val request =
            TransactWriteItemsRequest
                .builder()
                .transactItems(updates)
                .returnConsumedCapacity(ReturnConsumedCapacity.TOTAL)
                .build()

        dynamoDbClient.transactWriteItems(request)
    }
}

interface EntityToDynamoDbMap<T : EntityMarker> {
    fun toDynamoDbMap(entity: T): Put
}

class MonthlyBalanceToDynamoDbMap : EntityToDynamoDbMap<MonthlyBalance> {
    override fun toDynamoDbMap(entity: MonthlyBalance): Put {
        val attributes =
            mapOf(
                "account_id" to AttributeValue.builder().s(entity.accountId.value).build(),
                "year_month" to AttributeValue.builder().s("${entity.yearMonth.year}${entity.yearMonth.month}").build(),
                "balance" to AttributeValue.builder().n(entity.balance.toString()).build(),
                "currency" to AttributeValue.builder().s(entity.currency).build(),
                "last_updated" to AttributeValue.builder().s(entity.lastUpdated.toString()).build(),
            )

        return Put
            .builder()
            .tableName("MonthlyBalance")
            .item(attributes)
            .conditionExpression("attribute_not_exists(account_id) AND attribute_not_exists(year_month)")
            .build()
    }
}

class TransactionEventToDynamoDbMap : EntityToDynamoDbMap<TransactionEvent> {
    override fun toDynamoDbMap(entity: TransactionEvent): Put {
        val attributes =
            mapOf(
                "transaction_id" to AttributeValue.builder().s(entity.transactionId).build(),
                "timestamp" to AttributeValue.builder().s(entity.timestamp.toString()).build(),
                "amount" to AttributeValue.builder().n(entity.amount.toString()).build(),
                "account_id" to AttributeValue.builder().s(entity.accountId.value).build(),
                "description" to AttributeValue.builder().s(entity.description).build(),
                "transaction_type" to AttributeValue.builder().s(entity.transactionType).build(),
                "status" to AttributeValue.builder().s(entity.status).build(),
            )

        return Put
            .builder()
            .tableName("TransactionEvent")
            .item(attributes)
            .expressionAttributeNames(mapOf("#ts" to "timestamp"))
            .conditionExpression("attribute_not_exists(transaction_id) AND attribute_not_exists(#ts)")
            .build()
    }
}
