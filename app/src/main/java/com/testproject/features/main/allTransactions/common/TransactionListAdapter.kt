package com.testproject.features.main.allTransactions.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.testproject.R
import com.testproject.domain.entity.TransactionDetails

class TransactionListAdapter :
    ListAdapter<TransactionDetails, TransactionListViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_transaction, parent, false)

        return TransactionListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionListViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }
}

