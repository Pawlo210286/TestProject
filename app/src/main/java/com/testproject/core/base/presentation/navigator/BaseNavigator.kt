package com.testproject.core.base.presentation.navigator

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.testproject.R
import com.testproject.core.base.presentation.BaseActivity
import java.lang.ref.WeakReference

abstract class BaseNavigator {

    private var activityWr = WeakReference<BaseActivity>(null)

    protected val activity: BaseActivity?
        get() = activityWr.get()
    protected val fManager: FragmentManager?
        get() = activity?.supportFragmentManager

    fun attachActivity(activity: BaseActivity) {
        activityWr = WeakReference(activity)
    }

    fun activityDetached() {
        activityWr = WeakReference<BaseActivity>(null)
    }

    fun isContainerEmpty(@IdRes containerId: Int): Boolean {
        return fManager?.findFragmentById(containerId) == null
    }
}
