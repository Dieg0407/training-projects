package com.dieg0407.dynamo.transactions

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import java.util.UUID

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionsApplicationTests {
    @Autowired
    lateinit var webClient: WebTestClient

    @Test
    fun contextLoads() {
    }

    @Test
    fun shouldStoreBalanceAndPurchaseEvent() {
        val randomTransactionId = UUID.randomUUID().toString()
        val payload =
            """
            {
              "transactionId": "$randomTransactionId",
              "accountId": "acc-$randomTransactionId",
              "timestamp": "2024-06-15T10:00:00Z",
              "amount": 100.0,
              "description": "Grocery Shopping",
              "transactionType": "DEBIT",
              "status": "COMPLETED"
            }
            """.trimIndent()

        // Here you would typically call a service method to process the payload
        // and then verify that both MonthlyBalance and PurchaseEvent are stored correctly.
        webClient
            .post()
            .uri("/transactions?withBalance=true")
            .bodyValue(payload)
            .header("Content-Type", "application/json")
            .exchange()
            .expectStatus()
            .isOk
    }

    @Test
    fun shouldPreventDuplicatedEventsFromBeingStored() {
        val randomTransactionId = UUID.randomUUID().toString()
        val payload =
            """
            {
              "transactionId": "$randomTransactionId",
              "accountId": "acc-$randomTransactionId",
              "timestamp": "2024-06-16T11:00:00Z",
              "amount": 50.0,
              "description": "Fuel",
              "transactionType": "DEBIT",
              "status": "COMPLETED"
            }
            """.trimIndent()

        // First attempt to store the event
        webClient
            .post()
            .uri("/transactions")
            .bodyValue(payload)
            .header("Content-Type", "application/json")
            .exchange()
            .expectStatus()
            .isOk

        // Second attempt to store the same event should be prevented
        webClient
            .post()
            .uri("/transactions?withBalance=true")
            .bodyValue(payload)
            .header("Content-Type", "application/json")
            .exchange()
            .expectStatus()
            .is4xxClientError
    }
}
