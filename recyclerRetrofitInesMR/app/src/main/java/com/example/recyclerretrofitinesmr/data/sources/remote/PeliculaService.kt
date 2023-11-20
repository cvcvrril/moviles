package com.example.recyclerretrofitinesmr.data.sources.remote

import com.example.recyclerretrofitinesmr.data.model.PeliculaResponse
import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.example.recyclerretrofitinesmr.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PeliculaService {

    @GET(Constants.BASE_URL + "/peliculas/")
    suspend fun getAllPeliculas(): Response<List<PeliculaResponse>>

    @GET(Constants.BASE_URL + "/peliculas/{id}")
    suspend fun getPelicula(@Path("id") peliculaId: Int): Response<PeliculaResponse>

    @POST(Constants.BASE_URL + "/peliculas/")
    suspend fun createPelicula(@Body pelicula: Pelicula): Response<PeliculaResponse>

    @DELETE(Constants.BASE_URL + "/peliculas/{id}")
    suspend fun deletePelicula(@Path("id") peliculaId: Int): Response<Unit>

}