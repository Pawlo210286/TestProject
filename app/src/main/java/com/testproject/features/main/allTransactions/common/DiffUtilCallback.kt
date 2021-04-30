package com.testproject.features.main.allTransactions.common

import androidx.recyclerview.widget.DiffUtil
import com.testproject.domain.entity.TransactionDetails

class DiffUtilCallback : DiffUtil.ItemCallback<TransactionDetails>() {
    override fun areItemsTheSame(
        oldItem: TransactionDetails,
        newItem: TransactionDetails
    ): Boolean {
        return oldItem.transactionId == newItem.transactionId
    }

    override fun areContentsTheSame(
        oldItem: TransactionDetails,
        newItem: TransactionDetails
    ): Boolean {
        return oldItem == newItem
    }
}