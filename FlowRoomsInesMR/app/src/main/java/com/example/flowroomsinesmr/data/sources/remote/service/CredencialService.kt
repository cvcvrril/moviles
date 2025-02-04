package com.example.flowroomsinesmr.data.sources.remote.service

import com.example.flowroomsinesmr.data.modelo.response.CredencialResponse
import com.example.flowroomsinesmr.domain.modelo.AuthorizacionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface CredencialService {

    @GET("login")
    suspend fun getLogin(@Query("username") user:String, @Query("password") password:String): Response<AuthorizacionResponse>

    @POST("login")
    @Headers("Content-Type: application/json")
    suspend fun doRegister(@Body credencialResponse: CredencialResponse) : Response<Unit>

    @POST("login/forgotPassword")
    @Headers("Content-Type: application/json")
    suspend fun forgotPassword(@Body credencialResponse: CredencialResponse) : Response<Unit>


    @POST("login/refreshToken")
    suspend fun refreshToken(@Header("Authorization") token: String): Response<Unit>


}