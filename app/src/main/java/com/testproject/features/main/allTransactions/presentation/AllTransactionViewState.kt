package com.testproject.features.main.allTransactions.presentation

import com.testproject.domain.entity.TransactionDetails

data class AllTransactionViewState(
    val userBalance: Double,
    val userTransactions: List<TransactionDetails>
)
