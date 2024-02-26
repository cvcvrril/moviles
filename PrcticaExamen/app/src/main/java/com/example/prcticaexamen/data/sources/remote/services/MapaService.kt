package com.example.prcticaexamen.data.sources.remote.services

import com.example.prcticaexamen.domain.model.Mapa
import retrofit2.Response
import retrofit2.http.GET

interface MapaService {

    @GET("mapas")
    suspend fun getMapas(): Response<List<Mapa>>


}