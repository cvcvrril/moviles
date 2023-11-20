package com.example.recyclerretrofitinesmr.data.sources.remote

import com.example.recyclerretrofitinesmr.data.model.DirectorResponse
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DirectorService {

    @GET(Constants.BASE_URL + Constants.PATH_DIR)
    suspend fun getAllDirector(): Response<List<DirectorResponse>>

    @GET(Constants.BASE_URL + Constants.PATH_DIR_ID)
    suspend fun getDirector(@Path(Constants.ID) directorId: Int): Response<DirectorResponse>

    @POST(Constants.BASE_URL + Constants.PATH_DIR)
    suspend fun createDirector(@Body director: Director): Response<DirectorResponse>

    @DELETE(Constants.BASE_URL + Constants.PATH_DIR_ID)
    suspend fun deleteDirector(@Path(Constants.ID) directorId: Int): Response<Unit>
}
