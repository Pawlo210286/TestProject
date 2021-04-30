package com.testproject.di

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.android.androidModule

object AppModule {

    fun get(application: Application) = Kodein.Module("App") {
        import(androidModule(application))
        import(DomainModule.get())
    }
}