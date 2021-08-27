package com.testproject.features.main.allTransactions.common

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.testproject.R
import com.testproject.core.util.DateUtils
import com.testproject.core.util.DateUtils.DATE_FORMAT_EEEE_DD_MMMM_YYYY
import com.testproject.core.util.DateUtils.DATE_FORMAT_HH_MM
import com.testproject.domain.entity.TransactionDetails
import com.testproject.domain.entity.TransactionType
import java.math.RoundingMode
import java.text.SimpleDateFormat

/**
 * @author Pawlo Nikitin
 */
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
        transactionDate?.text = DateUtils.convertDateToString(transactionItem.transactionDate, DATE_FORMAT_EEEE_DD_MMMM_YYYY)
        transactionTime?.text = DateUtils.convertDateToString(transactionItem.transactionDate, DATE_FORMAT_HH_MM)
        transactionSum?.text = when (transactionItem.transactionType) {
            TransactionType.DEPOSIT -> transactionItem.amount.toBigDecimal().setScale(2,RoundingMode.HALF_EVEN).toPlainString()
            TransactionType.WITHDRAW -> "-${transactionItem.amount}"
        }
    }
}