package com.vandana.gojekgitapp.ui.main

import com.vandana.gojekgitapp.di.ActivityScope
import com.vandana.gojekgitapp.ui.base.BaseViewModel
import com.vandana.gojekgitapp.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

@ActivityScope
class MainViewModel(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel( compositeDisposable, networkHelper){

    override fun onCreate() {

    }
}