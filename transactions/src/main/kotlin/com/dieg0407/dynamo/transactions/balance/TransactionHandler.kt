package com.dieg0407.dynamo.transactions.balance

interface TransactionHandler {
    fun save(vararg entities: EntityMarker)
}
