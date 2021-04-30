package com.testproject.features.main.allTransactions.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testproject.core.library.viewmodel.bindViewModel
import com.testproject.core.library.viewmodel.kodeinViewModel
import com.testproject.R
import com.testproject.core.base.presentation.BaseFragment
import com.testproject.core.base.presentation.ViewModelFactory
import com.testproject.core.library.live_data.observe
import com.testproject.databinding.FragmentTransactionsListBinding
import com.testproject.domain.entity.TransactionDetails
import com.testproject.features.main.allTransactions.common.TransactionListAdapter
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import java.math.RoundingMode

class AllTransactionFragment : BaseFragment(R.layout.fragment_transactions_list) {

    override val kodeinModule = Kodein.Module(this::class.java.simpleName) {
        bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }

        bindViewModel<AllTransactionViewModel>() with provider {
            AllTransactionViewModel(
                transactionsUseCase = instance(),
                allTransactionNavigator = instance()
            )
        }
    }

    private val viewModel: AllTransactionViewModel by kodeinViewModel()
    private var binding: FragmentTransactionsListBinding? = null
    private val adapter: TransactionListAdapter by lazy { TransactionListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTransactionsListBinding.bind(view)
        observeData()
        initListeners()
        initAdapter()
        viewModel.loadUserData()
    }

    private fun initAdapter() {
        binding?.rvTransactionList?.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = this@AllTransactionFragment.adapter.apply {
                registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        (layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                            positionStart,
                            0
                        )
                    }
                })
            }
        }
    }

    private fun initListeners() {
        binding?.addNewTransactionFb?.setOnClickListener {
            viewModel.navigateToAddNewTransaction()
        }
    }

    private fun observeData() = with(viewModel) {
        observe(balance, ::renderUserBalance)
        observe(transactionList, ::setUpTransactionsList)
    }

    private fun setUpTransactionsList(transactionList: List<TransactionDetails>) {
        adapter.submitList(transactionList.toMutableList())
    }

    private fun renderUserBalance(balance: Double) {
        binding?.tvUserBalance?.text = getString(
            R.string.dollar_suffix, balance.toBigDecimal().setScale(
                2,
                RoundingMode.HALF_EVEN
            ).toPlainString())
    }

    companion object {
        fun newInstance() = AllTransactionFragment()
    }
}
