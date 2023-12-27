package com.example.flowroomsinesmr.data.sources.remote

import com.example.flowroomsinesmr.data.modelo.response.CredencialResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CredencialService {

    @GET("http://localhost:8080/videojuegosServidor-1.0-SNAPSHOT/api/login")
    suspend fun getLogin(@Query("user") user:String, @Query("password") password:String): Response<CredencialResponse>


}