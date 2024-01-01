package com.example.flowroomsinesmr.data.sources.remote

import com.example.flowroomsinesmr.data.modelo.response.CredencialResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonajeService {

    @GET("personajes")
    suspend fun getPersonajes(): Response<PersonajeService>

}