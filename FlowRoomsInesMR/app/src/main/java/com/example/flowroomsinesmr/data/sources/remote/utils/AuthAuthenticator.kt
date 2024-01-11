package com.example.flowroomsinesmr.data.sources.remote.utils

import com.example.flowroomsinesmr.data.sources.remote.service.CredencialService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

//TODO: COSAS QUE ARREGLAR ->


class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val accessToken = runBlocking {
            tokenManager.getAccessToken().first()
        }

        return runBlocking {
            val newAcessToken = getNewToken(accessToken)

            if (!newAcessToken.isSuccessful || newAcessToken.body() == null) { //Couldn't refresh the token, so restart the login process
                tokenManager.deleteAccessToken()
            }

            newAcessToken.body()?.let {
                tokenManager.saveAccessToken(it.token)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.token}")
                    .build()
            }
        }
    }

    //?????????????????????????????????????????

    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<Unit> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jwt-test-api.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val service = retrofit.create(CredencialService::class.java)
        return service.refreshToken("Bearer $refreshToken")
    }

}