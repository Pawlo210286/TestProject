package com.testproject.features.main.allTransactions.presentation

import com.testproject.domain.entity.TransactionDetails

/**
 * @author Pawlo Nikitin
 */
data class AllTransactionViewState(
    val userBalance: Double,
    val userTransactions: List<TransactionDetails>
)
