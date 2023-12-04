package com.example.practicaexamenmoviles.data.sources.remote

import com.example.practicaexamenmoviles.data.model.VideojuegoResponse
import retrofit2.Response
import retrofit2.http.GET

interface VideojuegoService {
    @GET("https://my-json-server.typicode.com/cvcvrril/REST-Videojuegos/videojuego/")
    suspend fun getAllVideojuegos(): Response<List<VideojuegoResponse>>

}