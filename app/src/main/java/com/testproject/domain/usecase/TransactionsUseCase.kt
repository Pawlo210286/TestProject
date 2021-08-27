package com.testproject.domain.usecase

import com.testproject.domain.entity.TransactionDetails
import com.testproject.domain.entity.UserTransactionData

/**
 * @author Pawlo Nikitin
 */
interface TransactionsUseCase {
    suspend fun insertNewTransaction(transactionDetails: TransactionDetails)
    suspend fun getAllTransactions(): UserTransactionData
}