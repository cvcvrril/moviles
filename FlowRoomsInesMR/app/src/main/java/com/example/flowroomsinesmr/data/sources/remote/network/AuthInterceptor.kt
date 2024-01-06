package com.example.flowroomsinesmr.data.sources.remote.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


/**
 * Intercepts and adds Token or API Key
 */

class AuthInterceptor(private val authorizationBearer: String) : Interceptor {

    private lateinit var accessToken: String
    private lateinit var refreshToken: String

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val header = original.header("Authorization")
        if (!header.isNullOrBlank()){
            val parts = header.split(" ")
            if (parts[0].equals("Bearer")){
                accessToken = parts [1]
                refreshToken = parts [2]
            }
        }

        val httpUrl = original.url.newBuilder()
            .addQueryParameter("Authorization", authorizationBearer)
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(httpUrl)

        return chain.proceed(requestBuilder.build())
    }

    fun getAccessToken(): String {
        return accessToken
    }

    fun getRefreshToken(): String {
        return refreshToken
    }
}