package com.example.aprobarines.data.sources.remote.service


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface VideojuegoService {

    @POST("getVideojuegos")
    suspend fun getVideojuegos() : Response<Unit>

}