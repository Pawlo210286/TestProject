package com.testproject.domain.entity

data class TransactionDetails(
    val transactionId: Long,
    val transactionDate: Long,
    val transactionType: TransactionType,
    val amount: Double
)
