package com.vandana.gojekgitapp.data.remote

import android.content.Context
import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.vandana.gojekgitapp.BuildConfig
import com.vandana.gojekgitapp.di.ApplicationContext
import com.vandana.gojekgitapp.utils.network.NetworkHelper
import com.vandana.gojekgitapp.utils.network.NetworkHelperImp
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Inject


//Class to create network call using Retrofit
object  Networking {

        private const val NETWORK_CALL_TIMEOUT = 60


    fun create(baseUrl: String, cacheDir: File, cacheSize: Long, networkHelper: NetworkHelper ): NetworkService {

            Log.v("TAG", "is init => "+ networkHelper.isNetworkConnected())
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(
                    OkHttpClient.Builder()
                        .cache(Cache(cacheDir, cacheSize))
                        .addNetworkInterceptor { chain ->
                            var request = chain.request()
                            request = if (networkHelper.isNetworkConnected())
                                request.newBuilder().header("Cache-Control", "max-age=" + 5).build()
                            else
                                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-age=" +  60 * 60 * 24 * 2).build()
                            chain.proceed(request)
                        }
                        .addInterceptor(
                            HttpLoggingInterceptor()
                                .apply {
                                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                    else HttpLoggingInterceptor.Level.NONE
                                })
                        .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                        .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NetworkService::class.java)
        }


}