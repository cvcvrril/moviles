package com.example.aprobarines.data.sources.remote

import com.example.aprobarines.data.modelo.response.AuthorizacionResponse
import com.example.aprobarines.data.sources.remote.service.UserService
import com.example.aprobarines.domain.modelo.User
import com.example.aprobarines.utils.NetworkResult
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private var service: UserService
) {

    suspend fun getLogin(username: String, password: String): NetworkResult<AuthorizacionResponse> {
        try {
            val response = service.getLogin(username, password)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    val accesToken = body.accessToken
                    val refreshToken = body.refreshToken
                    if (accesToken != null)
                        //tokenManager.saveAccessToken(accesToken)
                    return NetworkResult.Success(body)
                }

            } else {
                return NetworkResult.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }
        return NetworkResult.Error("Hubo un problema al iniciar sesión")
    }

    suspend fun doRegister(user: User): NetworkResult<Unit> {
        return try {
            val response = service.doRegister(user.toUserResponse())
            if (response.isSuccessful) {
                NetworkResult.Success(Unit)
            } else {
                NetworkResult.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: e.toString())
        }
    }

}