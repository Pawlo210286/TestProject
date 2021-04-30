package com.testproject

import android.app.Application
import com.testproject.di.AppModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import timber.log.Timber

class TestProjectApp : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(AppModule.get(this@TestProjectApp))
    }

    override val kodeinTrigger = KodeinTrigger()
    override fun onCreate() {
        super.onCreate()

        kodeinTrigger.trigger()
    }

}
