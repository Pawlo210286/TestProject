package com.testproject.features.main.newTransaction.presentation

import com.testproject.domain.entity.TransactionType

/**
 * @author Pawlo Nikitin
 */
data class AddNewTransactionViewState(
    val transactionType: TransactionType? = null,
    val amount: Double? = null,
    val errorMessage: Int? = null
)
