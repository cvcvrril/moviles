package com.example.recyclerretrofitinesmr.data.sources.remote

import com.example.recyclerretrofitinesmr.data.model.PeliculaResponse
import com.example.recyclerretrofitinesmr.utils.Constants
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeliculaService {

    @GET(Constants.BASE_URL + Constants.PATH_PELI)
    suspend fun getAllPeliculas(): Response<List<PeliculaResponse>>

    @GET(Constants.BASE_URL + Constants.PATH_PELI_ID)
    suspend fun getPelicula(@Path(Constants.ID) peliculaId: Int): Response<PeliculaResponse>

    @GET(Constants.BASE_URL + Constants.PATH_PELI)
    suspend fun getPeliculasIdDirector(@Query(Constants.ID_DIRECTOR) directorId: Int): Response<List<PeliculaResponse>>

    @DELETE(Constants.BASE_URL + Constants.PATH_PELI_ID)
    suspend fun deletePelicula(@Path(Constants.ID) peliculaId: Int): Response<Unit>

}