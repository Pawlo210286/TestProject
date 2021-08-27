package com.testproject.features.main.allTransactions.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testproject.core.library.live_data.delegate
import com.testproject.core.library.live_data.mapDistinct
import com.testproject.domain.usecase.TransactionsUseCase
import com.testproject.features.main.allTransactions.navigator.AllTransactionNavigator
import com.testproject.features.main.newTransaction.presentation.AddNewTransactionFragment
import kotlinx.coroutines.launch

/**
 * @author Pawlo Nikitin
 */
class AllTransactionViewModel(
    private val transactionsUseCase: TransactionsUseCase,
    private val allTransactionNavigator: AllTransactionNavigator
) : ViewModel() {

    private val viewState = MutableLiveData(initState())
    private var state: AllTransactionViewState by viewState.delegate()
    val balance = viewState.mapDistinct { it.userBalance }
    val transactionList = viewState.mapDistinct { it.userTransactions }

    /**
     * Replace current fragment to [AddNewTransactionFragment]
     * @see [com.testproject.features.main.MainNavigator.navigateToAddNewTransaction]
     */
    fun navigateToAddNewTransaction() {
        allTransactionNavigator.navigateToAddNewTransaction()
    }

    /**
     * Load all transactions info
     */
    fun loadUserData() {
        viewModelScope.launch {
            transactionsUseCase.getAllTransactions().let {
                state = state.copy(userBalance = it.userBalance, userTransactions = it.transactionData)
            }
        }
    }

    private fun initState(): AllTransactionViewState {
        return AllTransactionViewState(userBalance = 0.0, userTransactions = emptyList())
    }
}
