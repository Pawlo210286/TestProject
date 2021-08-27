package com.testproject.features.main.allTransactions.presentation

import com.testproject.domain.usecase.TransactionsUseCase
import com.testproject.features.main.allTransactions.navigator.AllTransactionNavigator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.testproject.domain.entity.TransactionDetails
import com.testproject.domain.entity.TransactionType
import com.testproject.domain.entity.UserTransactionData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class AllTransactionViewModelTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var transactionsUseCase: TransactionsUseCase
    @MockK
    lateinit var allTransactionNavigator: AllTransactionNavigator

    private lateinit var viewModel: AllTransactionViewModel

    private val dispatcher = TestCoroutineDispatcher()


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        viewModel = AllTransactionViewModel(transactionsUseCase, allTransactionNavigator)
    }

    @Test
    fun loadUserData() {
        val transactionData = UserTransactionData(
            2.0,
            listOf(
                TransactionDetails(
                    transactionId = 1L,
                    transactionDate = 0L,
                    transactionType = TransactionType.DEPOSIT,
                    amount = 1.0
                ),
                TransactionDetails(
                    transactionId = 2L,
                    transactionDate = 0L,
                    transactionType = TransactionType.DEPOSIT,
                    amount = 1.0
                )
            )
        )
        coEvery { transactionsUseCase.getAllTransactions() } returns transactionData
        viewModel.loadUserData()
        assert((viewModel.balance.value ?: 0.0) == 2.0)
    }
}