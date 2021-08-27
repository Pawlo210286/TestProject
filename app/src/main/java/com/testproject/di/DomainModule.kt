package com.testproject.di

import com.testproject.domain.repository.TransactionRepository
import com.testproject.domain.usecase.TransactionsInteractor
import com.testproject.domain.usecase.TransactionsUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * @author Pawlo Nikitin
 */
object DomainModule {
    fun get() = Kodein.Module("Domain") {

        bind<TransactionsUseCase>() with provider {
            TransactionsInteractor(transactionRepository = instance())
        }

        bind() from singleton {
            TransactionRepository()
        }
    }
}
