package com.dieg0407.dynamo.transactions.balance

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpStatusCodeException
import software.amazon.awssdk.services.dynamodb.model.TransactionCanceledException
import java.time.ZonedDateTime

@RestController
@RequestMapping("/transactions")
class TransactionEventController(
    val transactionHandler: TransactionHandler,
) {
    @GetMapping("/{id}")
    fun getTransactionEvent(
        @PathVariable id: String,
    ): String = "Transaction event with ID: $id"

    @DeleteMapping("/{id}")
    fun deleteTransactionEvent(
        @PathVariable id: String,
    ): String = "Transaction event with ID: $id deleted"

    @PostMapping(value = [""], consumes = ["application/json"])
    fun createTransactionEvent(
        @RequestBody payload: TransactionEventDto,
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
            transactionHandler.save(transactionEvent)

            return ResponseEntity.ok().build()
        } catch (e: TransactionCanceledException) {
            e.printStackTrace(System.err)
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
