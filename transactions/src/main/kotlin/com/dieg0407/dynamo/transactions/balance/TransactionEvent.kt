package com.dieg0407.dynamo.transactions.balance

import java.time.LocalDateTime

class TransactionEvent(
    val transactionId: String,
    val timestamp: LocalDateTime,
    var accountId: AccountId,
    var amount: Double,
    var description: String,
    var transactionType: String,
    var status: String,
) : EntityMarker
