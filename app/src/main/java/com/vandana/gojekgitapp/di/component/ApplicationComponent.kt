package com.vandana.gojekgitapp.di.component

import android.app.Application
import android.content.Context
import com.vandana.gojekgitapp.GoJekApplication
import com.vandana.gojekgitapp.data.remote.NetworkService
import com.vandana.gojekgitapp.data.repository.FetchDataRepository
import com.vandana.gojekgitapp.di.ApplicationContext
import com.vandana.gojekgitapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: GoJekApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getApplication(): Application

    fun getNetworkService(): NetworkService

    fun getDeliveryRepository(): FetchDataRepository


}