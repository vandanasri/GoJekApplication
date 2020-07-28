package com.vandana.gojekgitapp.ui.trendingRepoFragment

import com.vandana.gojekgitapp.data.repository.FetchDataRepository
import com.vandana.gojekgitapp.di.FragmentScope
import com.vandana.gojekgitapp.ui.base.BaseViewModel
import com.vandana.gojekgitapp.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

@FragmentScope
class TrendingRepositoryViewModel(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val fetchDataRepository: FetchDataRepository
)
    : BaseViewModel(compositeDisposable,networkHelper)  {





    override fun onCreate() {}


}