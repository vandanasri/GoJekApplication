package com.vandana.gojekgitapp.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vandana.gojekgitapp.di.ActivityContext
import com.vandana.gojekgitapp.ui.base.BaseActivity
import com.vandana.gojekgitapp.ui.main.MainViewModel
import com.vandana.gojekgitapp.utils.ViewModelProviderFactory
import com.vandana.gojekgitapp.utils.network.NetworkHelper
import com.vandana.gojekgitapp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @ActivityContext
    @Provides
    fun provideContext(): Context = activity

    @Provides
    fun provideMainViewModel(compositeDisposable: CompositeDisposable,
                             networkHelper: NetworkHelper
    ): MainViewModel =
        ViewModelProviders.of(activity, ViewModelProviderFactory(MainViewModel::class){
            MainViewModel(compositeDisposable,networkHelper)
        }
        ).get(MainViewModel::class.java)

}