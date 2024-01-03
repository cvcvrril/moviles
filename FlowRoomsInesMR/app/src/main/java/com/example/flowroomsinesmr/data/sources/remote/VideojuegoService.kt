package com.example.flowroomsinesmr.data.sources.remote

import com.example.flowroomsinesmr.data.modelo.response.VideojuegoResponse
import retrofit2.Response
import retrofit2.http.GET

interface VideojuegoService {

    @GET("videojuegos")
    suspend fun getAllVideojuegos() : Response<VideojuegoResponse>

}