package com.example.recyclerretrofitinesmr.data.sources.remote

import com.example.recyclerretrofitinesmr.data.model.BaseApiResponse
import com.example.recyclerretrofitinesmr.data.model.DirectorResponse
import com.example.recyclerretrofitinesmr.data.model.toDirector
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val directorService: DirectorService):BaseApiResponse() {

    suspend fun getDirector() =safeApiCall(apiCall = {directorService.getDirector()}, transform = DirectorResponse::toDirector)
}