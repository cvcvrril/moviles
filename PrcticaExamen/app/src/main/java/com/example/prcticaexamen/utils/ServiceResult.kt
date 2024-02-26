package com.example.prcticaexamen.utils


sealed class ServiceResult<T>(
    var data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : ServiceResult<T>(data)
    class Error<T>(message: String, data: T? = null) : ServiceResult<T>(data, message)
    class Loading<T> : ServiceResult<T>()
}