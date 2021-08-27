package com.testproject.domain.entity.error

import com.testproject.R

/**
 * @author Pawlo Nikitin
 */
class NotEnoughMoneyException(val errorRes: Int = R.string.error_not_enough_money) : RuntimeException()