package com.testproject.core.base.presentation.navigator

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.testproject.R
import com.testproject.core.base.presentation.BaseActivity
import java.lang.ref.WeakReference

/**
 * @author Pawlo Nikitin
 */
abstract class BaseNavigator {

    private var activityWr = WeakReference<BaseActivity>(null)

    protected val activity: BaseActivity?
        get() = activityWr.get()
    protected val fManager: FragmentManager?
        get() = activity?.supportFragmentManager

    /**
     * Reinitialize activity reference with new activity
     * @param activity current Activity
     */
    fun attachActivity(activity: BaseActivity) {
        activityWr = WeakReference(activity)
    }

    /**
     * Remove reference with current activity
     */
    fun activityDetached() {
        activityWr = WeakReference<BaseActivity>(null)
    }

    /**
     * Check if [androidx.fragment.app.FragmentManager] hasn't fragment with such id
     * @param containerId expected fragment id
     * @return boolean if [androidx.fragment.app.FragmentManager] contains expected fragment
     */
    fun isContainerEmpty(@IdRes containerId: Int): Boolean {
        return fManager?.findFragmentById(containerId) == null
    }
}
