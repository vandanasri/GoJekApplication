package com.vandana.gojekgitapp.data.remote

import com.vandana.gojekgitapp.data.model.TrendingRepositoryData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface NetworkService {

    //Created api to interact with server and fetch Trending Git Repositories
    @GET(Endpoints.REPOSITORY_DATA)
    fun fetchTrendingGitRepoData(@Query("language") language: String?,
                            @Query("since") since: String?): Single<List<TrendingRepositoryData>>
}