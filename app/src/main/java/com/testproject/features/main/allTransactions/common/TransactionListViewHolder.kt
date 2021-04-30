package com.testproject.features.main.allTransactions.common

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.testproject.R
import com.testproject.domain.entity.TransactionDetails
import com.testproject.domain.entity.TransactionType
import java.math.RoundingMode
import java.text.SimpleDateFormat

class TransactionListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var transactionDate: TextView? = null
    var transactionTime: TextView? = null
    var transactionSum: TextView? = null

    init {
        transactionDate = itemView.findViewById(R.id.tvTransactionDate)
        transactionTime = itemView.findViewById(R.id.tvTransactionTime)
        transactionSum = itemView.findViewById(R.id.tvTransactionSum)
    }

    fun bind(
        transactionItem: TransactionDetails
    ) {
        transactionDate?.text =
            SimpleDateFormat("EEEE, dd MMMM yyyy").format(transactionItem.transactionDate)
        transactionTime?.text =
            SimpleDateFormat("HH:mm").format(transactionItem.transactionDate)
        transactionSum?.text = when (transactionItem.transactionType) {
            TransactionType.DEPOSIT -> transactionItem.amount.toBigDecimal().setScale(2,RoundingMode.HALF_EVEN).toPlainString()
            TransactionType.WITHDRAW -> "-${transactionItem.amount}"
        }
    }
}