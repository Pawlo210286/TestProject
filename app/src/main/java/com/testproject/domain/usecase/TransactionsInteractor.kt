package com.testproject.domain.usecase

import com.testproject.domain.entity.TransactionDetails
import com.testproject.domain.entity.UserTransactionData
import com.testproject.domain.repository.TransactionRepository

/**
 * @author Pawlo Nikitin
 */
class TransactionsInteractor(private val transactionRepository: TransactionRepository) : TransactionsUseCase {
    override suspend fun insertNewTransaction(transactionDetails: TransactionDetails) {
        transactionRepository.insertNewTransaction(transactionDetails)
    }

    override suspend fun getAllTransactions(): UserTransactionData {
        return transactionRepository.getUserData()
    }
}