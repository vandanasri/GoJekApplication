package com.vandana.gojekgitapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vandana.gojekgitapp.R
import com.vandana.gojekgitapp.utils.common.Resource
import com.vandana.gojekgitapp.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import javax.net.ssl.HttpsURLConnection


abstract class BaseViewModel (
    val compositeDisposable: CompositeDisposable,
    private val networkHelper: NetworkHelper
): ViewModel(){


    val messageStringId = MutableLiveData<Resource<Int>>()

    val messageString = MutableLiveData<Resource<String>>()


    //to check network state
    protected fun checkInternetConnectionWithMessage(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
            false
        }

    protected fun checkInternetConnection() : Boolean = networkHelper.isNetworkConnected()


    protected fun handleNetworkError(err : Throwable?){
        err?.let {
            networkHelper.castToNetworkError(it).run {
                println("statusCode ${it.localizedMessage}")
                when (status) {
                    -1 -> messageStringId.postValue(Resource.error(R.string.network_default_error))
                    0 -> messageStringId.postValue(Resource.error(R.string.server_connection_error))
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        messageStringId.postValue(Resource.error(R.string.server_connection_error))
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR ->
                        messageStringId.postValue(Resource.error(R.string.network_internal_error))
                    HttpsURLConnection.HTTP_UNAVAILABLE ->
                        messageStringId.postValue(Resource.error(R.string.network_server_not_available))
                    else -> messageString.postValue(Resource.error(message))
                }
            }
        }
    }



    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }



    abstract fun onCreate()

}