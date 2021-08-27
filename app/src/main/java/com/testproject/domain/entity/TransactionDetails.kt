package com.testproject.domain.entity

/**
 * @author Pawlo Nikitin
 */
data class TransactionDetails(
    val transactionId: Long,
    val transactionDate: Long,
    val transactionType: TransactionType,
    val amount: Double
)
