package com.testproject.core.base.presentation

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein

/**
 * @author Pawlo Nikitin
 */
abstract class BaseActivity(
    @LayoutRes
    layoutId: Int
) : AppCompatActivity(layoutId), KodeinAware {

    private val _parentKodein by closestKodein()
    override val kodein: Kodein by retainedKodein {
        extend(_parentKodein)
        import(diModule(), allowOverride = true)
    }
    abstract fun diModule(): Kodein.Module

}
