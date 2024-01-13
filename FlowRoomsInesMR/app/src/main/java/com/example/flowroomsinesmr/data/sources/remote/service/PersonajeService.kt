package com.example.flowroomsinesmr.data.sources.remote.service

import retrofit2.Response
import retrofit2.http.GET

interface PersonajeService {

    @GET("personajes/{idVideojuego}")
    suspend fun getPersonajes(): Response<List<PersonajeService>>

}