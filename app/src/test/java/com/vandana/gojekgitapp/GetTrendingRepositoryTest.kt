package com.vandana.gojekgitapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.vandana.gojekgitapp.data.model.TrendingRepositoryData
import com.vandana.gojekgitapp.data.repository.FetchDataRepository
import com.vandana.gojekgitapp.ui.trendingRepoFragment.TrendingRepositoryViewModel
import com.vandana.gojekgitapp.utils.common.Resource
import com.vandana.gojekgitapp.utils.common.Status
import com.vandana.gojekgitapp.utils.network.NetworkHelper
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetTrendingRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private  lateinit var fetchDataRepository: FetchDataRepository

    @Mock
    private lateinit var isLoadingObserver: Observer<Resource<Status>>

    @Mock
    private lateinit var messageStringIdObserver: Observer<Resource<Int>>

    private lateinit var testScheduler: TestScheduler

    private lateinit var fragViewModel: TrendingRepositoryViewModel


    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()

        fragViewModel = TrendingRepositoryViewModel(
            compositeDisposable,
            networkHelper,
            fetchDataRepository
        )

        fragViewModel.isLoading.observeForever(isLoadingObserver)
        fragViewModel.messageStringId.observeForever(messageStringIdObserver)
    }


    @Test
    fun givenServerResponse200_whenTrendingRepositoryApiCall(){

        val trendingRepoResponse = TrendingRepositoryData("Author1","Name1","Aavtar1","url1","description1",
        "language","langColor1",1,1,1,
            listOf(TrendingRepositoryData.BuiltBy("href1","avtar1","username1")))

        val trendingRepoResponse2 = TrendingRepositoryData("Author1","Name1","Aavtar1","url1","description1",
            "language","langColor1",1,1,1,
            listOf(TrendingRepositoryData.BuiltBy("href1","avtar1","username1")))

        var trendingRepoList = listOf(trendingRepoResponse,trendingRepoResponse2)


        doReturn(true)
            .`when`(networkHelper)
            .isNetworkConnected()
        doReturn(Single.just(trendingRepoList))
            .`when`(fetchDataRepository)
            .getTrendingRepositoryData("kotlin", "weekly")
        fragViewModel.callTrendingRepositoryAPI()

        testScheduler.triggerActions()

        verify(isLoadingObserver).onChanged(Resource.loading())
        verify(isLoadingObserver).onChanged(Resource.success())
    }



    @Test
    fun givenNoInternet_whenTrendingApiCall_shouldShowData() {

        val trendingRepoResponse = TrendingRepositoryData("Author1","Name1","Aavtar1","url1","description1",
            "language","langColor1",1,1,1,
            listOf(TrendingRepositoryData.BuiltBy("href1","avtar1","username1")))

        val trendingRepoList = listOf(trendingRepoResponse)


        doReturn(false)
            .`when`(networkHelper)
            .isNetworkConnected()
        doReturn(Single.just(trendingRepoList))
            .`when`(fetchDataRepository)
            .getTrendingRepositoryData("kotlin", "weekly")
        fragViewModel.callTrendingRepositoryAPI()
        testScheduler.triggerActions()

        verify(isLoadingObserver).onChanged(Resource.success())

    }


    @After
    fun tearDown(){
        fragViewModel.isLoading.removeObserver(isLoadingObserver)
        fragViewModel.messageStringId.removeObserver(messageStringIdObserver)
    }

}