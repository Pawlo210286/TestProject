package com.testproject.domain.entity

data class UserTransactionData(
    val userBalance: Double,
    val transactionData: List<TransactionDetails>
)
