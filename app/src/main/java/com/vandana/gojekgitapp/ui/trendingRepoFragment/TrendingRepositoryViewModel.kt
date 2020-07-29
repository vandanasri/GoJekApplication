package com.vandana.gojekgitapp.ui.trendingRepoFragment

import androidx.lifecycle.MutableLiveData
import com.vandana.gojekgitapp.data.model.TrendingRepositoryData
import com.vandana.gojekgitapp.data.repository.FetchDataRepository
import com.vandana.gojekgitapp.di.FragmentScope
import com.vandana.gojekgitapp.ui.base.BaseViewModel
import com.vandana.gojekgitapp.utils.common.Resource
import com.vandana.gojekgitapp.utils.common.Status
import com.vandana.gojekgitapp.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@FragmentScope
class TrendingRepositoryViewModel(compositeDisposable: CompositeDisposable, networkHelper: NetworkHelper,
    private val fetchDataRepository: FetchDataRepository
)
    : BaseViewModel(compositeDisposable,networkHelper)  {

    var isLoading : MutableLiveData<Resource<Status>> = MutableLiveData()

    val trendingRepositoryLiveData : MutableLiveData<List<TrendingRepositoryData>> = MutableLiveData()

    var language : String = "kotlin"
    var since : String = "Weekly"

    fun callTrendingRepositoryAPI(){
        val paramMap = HashMap<String, String>()
        paramMap["language"] = language
        paramMap["since"] = since
        if (checkInternetConnectionWithMessage()) {
            isLoading.postValue(Resource.loading())
            compositeDisposable.add(
                fetchDataRepository.getTrendingRepositoryData(language,since)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            trendingRepositoryLiveData.postValue(it)
                            isLoading.postValue(Resource.success())
                        },
                        {
                            isLoading.postValue(Resource.error())
                            handleNetworkError(it)
                        })
            )
        }
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    override fun onCreate() {}


}