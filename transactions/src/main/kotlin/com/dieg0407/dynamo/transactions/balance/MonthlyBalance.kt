package com.dieg0407.dynamo.transactions.balance

import java.time.LocalDateTime

class MonthlyBalance(
    val accountId: AccountId,
    val yearMonth: YearMonth,
    var balance: Double,
    var currency: String,
    var lastUpdated: LocalDateTime,
) : EntityMarker

data class AccountId(
    val value: String,
) {
    init {
        if (value.isBlank()) {
            throw IllegalArgumentException("AccountId cannot be blank")
        }
    }
}

// format YYYYMM
data class YearMonth(
    val year: String,
    val month: String,
) {
    init {
        val monthInt = month.toIntOrNull()
        if (year.length != 4 || month.length != 2 || monthInt == null || monthInt !in 1..12) {
            throw IllegalArgumentException("Invalid YearMonth format")
        }
    }
}
