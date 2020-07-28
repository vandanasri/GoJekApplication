package com.vandana.gojekgitapp.utils.network

interface NetworkHelper {

    fun isNetworkConnected(): Boolean

    fun castToNetworkError(throwable: Throwable): NetworkError
}