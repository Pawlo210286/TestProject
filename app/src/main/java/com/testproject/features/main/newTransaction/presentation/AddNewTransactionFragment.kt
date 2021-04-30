package com.testproject.features.main.newTransaction.presentation

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.testproject.core.library.viewmodel.bindViewModel
import com.testproject.core.library.viewmodel.kodeinViewModel
import com.testproject.R
import com.testproject.core.base.presentation.BaseFragment
import com.testproject.core.base.presentation.ViewModelFactory
import com.testproject.core.library.live_data.observe
import com.testproject.databinding.FragmentTransactionsBinding
import com.testproject.domain.entity.TransactionType
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AddNewTransactionFragment : BaseFragment(R.layout.fragment_transactions) {
    override val kodeinModule = Kodein.Module(this::class.java.simpleName) {
        bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }

        bindViewModel<AddNewTransactionViewModel>() with provider {
            AddNewTransactionViewModel(transactionsUseCase = instance(), navigator = instance())
        }
    }

    private val viewModel: AddNewTransactionViewModel by kodeinViewModel()
    private lateinit var binding: FragmentTransactionsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTransactionsBinding.bind(view)
        observeData()
        initAdapter()
        initListeners()
    }

    private fun initAdapter() {
        val items = resources.getStringArray(R.array.transactions)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_option, items)
        (binding.atvChooseOption as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun observeData() = with(viewModel) {
        observe(errorMsg, ::renderError)
    }

    private fun renderError(errorRes: Int?) {
        if (errorRes != null) {
            Toast.makeText(requireContext(), getString(errorRes), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListeners() {
        binding.apply {
            etTransactionSum.doAfterTextChanged {
                viewModel.setAmount(it.toString())
                updatedButtonState()
            }
            atvChooseOption.setOnItemClickListener { _, _, position, _ ->
                viewModel.setTransactionType(if (position == TransactionType.DEPOSIT.ordinal) TransactionType.DEPOSIT else TransactionType.WITHDRAW)
                updatedButtonState()
            }
            bSubmit.setOnClickListener {
                viewModel.saveTransaction()
            }
            navigateBack.setOnClickListener {
                viewModel.navigateBack()
            }
        }
    }

    private fun updatedButtonState() {
        binding.bSubmit.isEnabled = viewModel.isStateValid()
    }

    companion object {
        fun newInstance() = AddNewTransactionFragment()
    }
}