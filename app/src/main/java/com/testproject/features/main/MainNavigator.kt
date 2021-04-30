package com.testproject.features.main

import androidx.fragment.app.commit
import com.testproject.R
import com.testproject.core.base.presentation.navigator.BaseNavigator
import com.testproject.features.main.allTransactions.navigator.AllTransactionNavigator
import com.testproject.features.main.allTransactions.presentation.AllTransactionFragment
import com.testproject.features.main.newTransaction.navigator.AddNewTransactionNavigator
import com.testproject.features.main.newTransaction.presentation.AddNewTransactionFragment

class MainNavigator : AllTransactionNavigator, AddNewTransactionNavigator, BaseNavigator() {
    fun navigateToAllTransactions() {
        fManager?.commit {
            replace(R.id.activity_container, AllTransactionFragment.newInstance())
        }
    }

    override fun goBack() {
        activity?.onBackPressed()
    }

    override fun navigateToAddNewTransaction() {
        fManager?.commit {
            addToBackStack(AddNewTransactionFragment::class.java.simpleName)
            replace(R.id.activity_container, AddNewTransactionFragment.newInstance())
        }
    }
}
