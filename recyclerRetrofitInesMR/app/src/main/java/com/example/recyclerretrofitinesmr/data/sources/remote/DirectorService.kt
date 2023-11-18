package com.example.recyclerretrofitinesmr.data.sources.remote

import com.example.recyclerretrofitinesmr.data.model.DirectorResponse
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DirectorService {

    @GET(Constants.BASE_URL)
    suspend fun getAllDirector(): Response<List<DirectorResponse>>

    @GET("prueba")
    suspend fun getDirector(): Response<DirectorResponse>
}