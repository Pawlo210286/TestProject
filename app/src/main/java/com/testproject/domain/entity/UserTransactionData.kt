package com.testproject.domain.entity

/**
 * @author Pawlo Nikitin
 */
data class UserTransactionData(
    val userBalance: Double,
    val transactionData: List<TransactionDetails>
)
