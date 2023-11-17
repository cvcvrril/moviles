package com.example.recyclerretrofitinesmr.utils

import com.example.recyclerretrofitinesmr.domain.Director

sealed class NetworkResultt<T>(
    var data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : NetworkResultt<T>(data)

    class Error<T>(message: String, data: T? = null) : NetworkResultt<T>(data, message)

    class Loading<T> : NetworkResultt<T>()


    fun <R> map( transform :(data: T?) -> Director) : NetworkResultt<Director> =
        when(this){
            is Error -> Error(message!!,transform(data))
            is Loading -> Loading()
            is Success -> Success(transform(data))
        }

}