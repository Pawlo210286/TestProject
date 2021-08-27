package com.testproject.core.library.view

import android.text.InputFilter
import android.text.Spanned
import android.widget.EditText
import java.util.regex.*

fun EditText.inputFilterDecimal(
    maxDigitsBeforeDecimalPoints: Int,
    maxDigitsAfterDecimalPoints: Int
){
    val pattern: Pattern = Pattern.compile(
        "[0-9]{0," + (maxDigitsBeforeDecimalPoints - 1).toString() + "}+((\\.[0-9]{0,"
                + (maxDigitsAfterDecimalPoints - 1).toString() + "})?)||(\\.)?"
    )
    filters = arrayOf(object : InputFilter {
        override fun filter(
            source: CharSequence?,
            start: Int,
            end: Int,
            dest: Spanned?,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            dest?.apply {
                val matcher: Matcher = pattern.matcher(dest)
                return if (!matcher.matches()) "" else null
            }
            return null
        }
    })
}