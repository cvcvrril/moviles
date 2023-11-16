package com.example.recyclerretrofitinesmr.data.sources.remote

import com.example.recyclerretrofitinesmr.data.model.DirectorResponse
import com.example.recyclerretrofitinesmr.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface DirectorService {

@GET(Constants.BASE_URL)
suspend fun getDirector(): Response<DirectorResponse>
}