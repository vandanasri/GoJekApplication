package com.vandana.gojekgitapp

import android.app.Application
import com.vandana.gojekgitapp.di.component.ApplicationComponent
import com.vandana.gojekgitapp.di.component.DaggerApplicationComponent
import com.vandana.gojekgitapp.di.module.ApplicationModule

class GoJekApplication :Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}