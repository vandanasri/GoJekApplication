package com.vandana.gojekgitapp.di.module

import android.app.Application
import android.content.Context
import com.vandana.gojekgitapp.BuildConfig
import com.vandana.gojekgitapp.GoJekApplication
import com.vandana.gojekgitapp.data.remote.NetworkService
import com.vandana.gojekgitapp.data.remote.Networking
import com.vandana.gojekgitapp.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: GoJekApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application


    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

}