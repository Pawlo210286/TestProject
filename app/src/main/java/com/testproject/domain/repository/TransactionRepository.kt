package com.testproject.domain.repository

import com.testproject.domain.entity.TransactionDetails
import com.testproject.domain.entity.TransactionType
import com.testproject.domain.entity.UserTransactionData
import com.testproject.domain.entity.error.NotEnoughMoneyException


class TransactionRepository {
    private var userBalance: Double = 0.0
    private val transactionsList = ArrayList<TransactionDetails>(0)

    suspend fun getUserData(): UserTransactionData {
        return UserTransactionData(userBalance = userBalance, transactionData = transactionsList.toList())
    }

    suspend fun insertNewTransaction(newTransaction: TransactionDetails) {
        when (newTransaction.transactionType) {
            TransactionType.DEPOSIT -> {
                userBalance += newTransaction.amount
            }
            TransactionType.WITHDRAW -> {
                if (userBalance < newTransaction.amount) {
                    throw NotEnoughMoneyException()
                } else {
                    userBalance -= newTransaction.amount
                }
            }
        }
        transactionsList.add(0, newTransaction)
    }
}