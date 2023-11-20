package com.example.recyclerretrofitinesmr.data.sources.remote

import com.example.recyclerretrofitinesmr.data.model.PeliculaResponse
import com.example.recyclerretrofitinesmr.utils.Constants
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeliculaService {

    @GET(Constants.BASE_URL + "/peliculas/")
    suspend fun getAllPeliculas(): Response<List<PeliculaResponse>>

    @GET(Constants.BASE_URL + "/peliculas/{id}")
    suspend fun getPelicula(@Path("id") peliculaId: Int): Response<PeliculaResponse>

    @GET(Constants.BASE_URL + "/peliculas/")
    suspend fun getPeliculasIdDirector(@Query("idDirector") directorId: Int): Response<List<PeliculaResponse>>

    @DELETE(Constants.BASE_URL + "/peliculas/{id}")
    suspend fun deletePelicula(@Path("id") peliculaId: Int): Response<Unit>

}