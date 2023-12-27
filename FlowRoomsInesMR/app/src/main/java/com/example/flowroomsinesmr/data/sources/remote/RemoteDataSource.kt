package com.example.flowroomsinesmr.data.sources.remote

import com.example.flowroomsinesmr.data.modelo.response.toCredencial
import com.example.flowroomsinesmr.domain.modelo.Credencial
import com.example.flowroomsinesmr.utils.NetworkResult
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val credencialService: CredencialService
) {

    //LOGIN
    suspend fun getLogin(user: String, password: String): NetworkResult<Credencial>{
        try {
            val response = credencialService.getLogin(user,password)
            if (response.isSuccessful){
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body.toCredencial())
                }
            }else{
                return NetworkResult.Error("${response.code()} ${response.message()}")
            }
        }catch (e: Exception){
            return NetworkResult.Error(e.message ?: e.toString())
        }
        return NetworkResult.Error("Hubo un problema al sacar el videojuego con el id proporcionado")
    }


    //TODO: HACER EL REGISTRO


}