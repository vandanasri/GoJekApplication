package com.vandana.gojekgitapp.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vandana.gojekgitapp.data.repository.FetchDataRepository
import com.vandana.gojekgitapp.ui.base.BaseFragment
import com.vandana.gojekgitapp.ui.trendingRepoFragment.TrendingRepositoryViewModel
import com.vandana.gojekgitapp.utils.ViewModelProviderFactory
import com.vandana.gojekgitapp.utils.network.NetworkHelper
import com.vandana.gojekgitapp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun providesTrendingRepositoryViewModel( compositeDisposable: CompositeDisposable,
                                            networkHelper: NetworkHelper,
                                            fetchDataRepository: FetchDataRepository

    ) : TrendingRepositoryViewModel =
        ViewModelProviders.of(fragment, ViewModelProviderFactory(TrendingRepositoryViewModel::class){
            TrendingRepositoryViewModel(compositeDisposable, networkHelper, fetchDataRepository)
        }).get(TrendingRepositoryViewModel::class.java)
}