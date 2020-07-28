package com.vandana.gojekgitapp.data.repository


import com.vandana.gojekgitapp.data.model.TrendingRepositoryData
import com.vandana.gojekgitapp.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject


// fetch repository will tell you from where to fetch the data from database or from network
class FetchDataRepository @Inject constructor(
    private val networkService: NetworkService
) {

    //fetching data from network service api
    fun fetchTrendingRepositoryData(language: String, since: String) : Single<List<TrendingRepositoryData>> =
        networkService.fetchTrendingGitRepoData(language, since)
}