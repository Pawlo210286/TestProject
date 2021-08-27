package com.testproject.core.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * @author Pawlo Nikitin
 */
object DateUtils {

    const val DATE_FORMAT_EEEE_DD_MMMM_YYYY = "EEEE, dd MMMM yyyy"
    const val DATE_FORMAT_HH_MM = "HH:mm"

    @SuppressLint("SimpleDateFormat")
    fun convertDateToString(date: Long, format: String): String{
        return SimpleDateFormat(format).format(date)
    }
}