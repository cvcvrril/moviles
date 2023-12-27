package com.example.flowroomsinesmr.data.sources.remote

import com.example.flowroomsinesmr.data.modelo.response.toCredencial
import com.example.flowroomsinesmr.domain.modelo.Credencial
import com.example.flowroomsinesmr.domain.modelo.toCredencialResponse
import com.example.flowroomsinesmr.utils.NetworkResult
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val credencialService: CredencialService
) {

    suspend fun getLogin(username: String, password: String): NetworkResult<Credencial> {
        try {
            val response = credencialService.getLogin(username, password)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body.toCredencial())
                }
            } else {
                return NetworkResult.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }
        return NetworkResult.Error("Hubo un problema al sacar el videojuego con el id proporcionado")
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


    //TODO: HACER EL REGISTRO


}