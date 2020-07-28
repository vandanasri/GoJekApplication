package com.vandana.gojekgitapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

class ViewModelProviderFactory<T: ViewModel> (private val kClass: KClass<T>,
                                              private val creator: () ->  T) : ViewModelProvider.NewInstanceFactory()
{

    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(kClass.java)) //check if it can be created or not
            return creator() as T
        throw IllegalAccessException("Unknown class name")
    }
}