package com.example.aprobarines.data.sources.remote.service

import com.example.aprobarines.data.modelo.request.AuthorizationRequest
import com.example.aprobarines.data.modelo.response.AuthorizacionResponse
import com.example.aprobarines.data.modelo.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {

    @GET("login")
    //@HTTP(method = "GET", path = "registro", hasBody = true)
    suspend fun getLogin(@Query("username") user:String, @Query("password") password:String): Response<AuthorizacionResponse>

    @POST("registro")
    suspend fun doRegister(@Body userResponse: UserResponse) : Response<Unit>

    @POST("refreshToken")
    suspend fun refreshToken(@Header("Authorization") token: String): Response<Unit>
}