package com.example.flowroomsinesmr.data.sources.remote

import com.example.flowroomsinesmr.data.modelo.entity.toVideojuego
import com.example.flowroomsinesmr.data.modelo.response.toCredencial
import com.example.flowroomsinesmr.data.sources.remote.service.CredencialService
import com.example.flowroomsinesmr.data.sources.remote.service.VideojuegoService
import com.example.flowroomsinesmr.data.sources.remote.utils.TokenManager
import com.example.flowroomsinesmr.domain.modelo.AuthorizacionResponse
import com.example.flowroomsinesmr.domain.modelo.Credencial
import com.example.flowroomsinesmr.domain.modelo.Videojuego
import com.example.flowroomsinesmr.domain.modelo.toCredencialResponse
import com.example.flowroomsinesmr.utils.NetworkResult
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val credencialService: CredencialService,
    private val videojuegoService: VideojuegoService,
    private val tokenManager: TokenManager,
) : BaseApiResponse() {

    suspend fun getLogin(username: String, password: String): NetworkResult<AuthorizacionResponse> {
        try {
            val response = credencialService.getLogin(username, password)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    val accesToken = body.accessToken
                    val refreshToken = body.refreshToken
                    if (accesToken != null)
                        tokenManager.saveAccessToken(accesToken)
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

    suspend fun doRegister(credencial: Credencial): NetworkResult<Unit> {
        return try {
            val response = credencialService.doRegister(credencial.toCredencialResponse())
            if (response.isSuccessful) {
                NetworkResult.Success(Unit)
            } else {
                NetworkResult.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: e.toString())
        }
    }

    suspend fun forgotPassword(credencial: Credencial): NetworkResult<Unit> {
        return try {
            val response = credencialService.forgotPassword(credencial.toCredencialResponse())
            if (response.isSuccessful) {
                NetworkResult.Success(Unit)
            } else {
                NetworkResult.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: e.toString())
        }
    }

    //VIDEOJUEGOS

    //TODO: ARREGLAR ESTO

    suspend fun getAllVideojuegosFlow(): NetworkResult<List<Videojuego>> {
        return safeApiCall(apiCall = { videojuegoService.getAllVideojuegos() },
            transform = { videojuegosResponse ->
                videojuegosResponse
                    .results?.map { videojuegoEntity -> videojuegoEntity.toVideojuego() }
                    ?: emptyList()
            })
    }

    //PERSONAJES


}