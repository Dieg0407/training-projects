package com.dieg0407.dynamo.transactions.balance

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import software.amazon.awssdk.services.dynamodb.model.TransactionCanceledException
import java.time.ZonedDateTime

@RestController
@RequestMapping("/transactions")
class TransactionEventController(
    val transactionHandler: TransactionHandler,
) {
    companion object {
        val logger = LoggerFactory.getLogger(Companion::class.java.name)
    }

    @PostMapping(value = [""], consumes = ["application/json"])
    fun createTransactionEvent(
        @RequestBody payload: TransactionEventDto,
        @RequestParam("withBalance", required = false, defaultValue = "false") withBalance: Boolean = false,
    ): ResponseEntity<Unit> {
        val transactionEvent =
            TransactionEvent(
                transactionId = payload.transactionId,
                accountId = AccountId(payload.accountId),
                timestamp = ZonedDateTime.parse(payload.timestamp).toLocalDateTime(),
                amount = payload.amount,
                description = payload.description,
                transactionType = payload.transactionType,
                status = payload.status,
            )

        try {
            if (!withBalance) {
                transactionHandler.save(transactionEvent)
                return ResponseEntity.ok().build()
            }

            val monthlyBalance =
                MonthlyBalance(
                    accountId = AccountId(payload.accountId),
                    yearMonth =
                        YearMonth(
                            transactionEvent.timestamp.year.toString(),
                            transactionEvent.timestamp.monthValue
                                .toString()
                                .padStart(2, '0'),
                        ),
                    balance = payload.amount,
                    currency = "USD",
                    lastUpdated = ZonedDateTime.parse(payload.timestamp).toLocalDateTime(),
                )

            transactionHandler.save(transactionEvent, monthlyBalance)

            return ResponseEntity.ok().build()
        } catch (e: TransactionCanceledException) {
            logger.error("Transaction Cancelled detected!", e)
            for (reason in e.cancellationReasons()) {
                logger.error("Cancellation reason: $reason")
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }
}

data class TransactionEventDto(
    val transactionId: String,
    val accountId: String,
    val timestamp: String,
    val amount: Double,
    val description: String,
    val transactionType: String,
    val status: String,
)
