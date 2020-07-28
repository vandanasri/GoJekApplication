package com.vandana.gojekgitapp.di.component

import com.vandana.gojekgitapp.GoJekApplication
import com.vandana.gojekgitapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: GoJekApplication)

}