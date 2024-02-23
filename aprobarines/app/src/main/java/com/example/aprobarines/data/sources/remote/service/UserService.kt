package com.example.aprobarines.data.sources.remote.service

import com.example.aprobarines.data.modelo.request.AuthorizationRequest
import com.example.aprobarines.data.modelo.response.AuthorizacionResponse
import com.example.aprobarines.data.modelo.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    @GET("login")
    suspend fun getLogin(@Body request : AuthorizationRequest ): Response<AuthorizacionResponse>

    @POST("registro")
    suspend fun doRegister(@Body userResponse: UserResponse) : Response<Unit>

    @POST("refreshToken")
    suspend fun refreshToken(@Header("Authorization") token: String): Response<Unit>
}