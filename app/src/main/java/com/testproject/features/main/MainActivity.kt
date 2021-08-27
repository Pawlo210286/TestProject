package com.testproject.features.main

import android.os.Bundle
import com.testproject.R
import com.testproject.core.base.presentation.BaseActivity
import com.testproject.features.main.allTransactions.navigator.AllTransactionNavigator
import com.testproject.features.main.newTransaction.navigator.AddNewTransactionNavigator
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * @author Pawlo Nikitin
 */
class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun diModule() = Kodein.Module(this::class.java.simpleName) {
        bind<MainNavigator>() with singleton { MainNavigator() }
        bind<AllTransactionNavigator>() with provider { navigator }
        bind<AddNewTransactionNavigator>() with provider { navigator }
    }

    private val navigator: MainNavigator by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.attachActivity(this)
        if (navigator.isContainerEmpty(R.id.activity_container)) {
            navigator.navigateToAllTransactions()
        }
    }

    override fun onDestroy() {
        navigator.activityDetached()
        super.onDestroy()
    }
}
