package com.testproject.features.main.newTransaction.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testproject.R
import com.testproject.core.library.live_data.delegate
import com.testproject.core.library.live_data.mapNotDistinct
import com.testproject.domain.entity.TransactionDetails
import com.testproject.domain.entity.TransactionType
import com.testproject.domain.entity.error.NotEnoughMoneyException
import com.testproject.domain.usecase.TransactionsUseCase
import com.testproject.features.main.newTransaction.navigator.AddNewTransactionNavigator
import kotlinx.coroutines.launch
import java.util.*

/**
 * @author Pawlo Nikitin
 */
class AddNewTransactionViewModel(
    private val transactionsUseCase: TransactionsUseCase,
    private val navigator: AddNewTransactionNavigator
) : ViewModel() {

    private val viewState = MutableLiveData(initState())
    private var state: AddNewTransactionViewState by viewState.delegate()
    val errorMsg = viewState.mapNotDistinct { it.errorMessage }

    fun saveTransaction() {
        viewModelScope.launch {
            try {
                if (validateAmount(state.amount) && state.transactionType != null) {
                    transactionsUseCase.insertNewTransaction(
                        TransactionDetails(
                            transactionId = Calendar.getInstance().timeInMillis,
                            amount = state.amount!!,
                            transactionType = state.transactionType!!,
                            transactionDate = Calendar.getInstance().timeInMillis
                        )
                    ).let {
                        navigator.goBack()
                    }
                }
            } catch (e: NotEnoughMoneyException) {
                state = state.copy(errorMessage = e.errorRes)
            }
        }
    }

    private fun validateAmount(amount: Double?): Boolean {
        amount?.toString()?.run {
            return if (toDouble() == 0.0) {
                state = state.copy(errorMessage = R.string.error_zero_value)
                false
            } else {
                true
            }
        }
        return false
    }

    fun setAmount(amount: String) {
        state = state.copy(amount = if (amount.isNotEmpty()) amount.toDouble() else null)
    }

    fun setTransactionType(transactionType: TransactionType) {
        state = state.copy(transactionType = transactionType)
    }

    fun isStateValid(): Boolean {
        return state.amount != null && state.transactionType != null
    }

    fun navigateBack() {
        navigator.goBack()
    }

    private fun initState(): AddNewTransactionViewState {
        return AddNewTransactionViewState(
            transactionType = null,
            amount = null,
            errorMessage = null
        )
    }
}