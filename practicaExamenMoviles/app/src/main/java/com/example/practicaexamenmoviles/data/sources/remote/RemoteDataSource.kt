package com.example.practicaexamenmoviles.data.sources.remote

import android.util.Log
import com.example.practicaexamenmoviles.data.model.toVideojuego
import com.example.practicaexamenmoviles.domain.model.Videojuego
import com.example.practicaexamenmoviles.utils.NetworkResult
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val videojuegoService: VideojuegoService, private val personajeService: PersonajeService
) {
     suspend fun getVideojuegos(): NetworkResult<List<Videojuego>> {
        try {
            val response = videojuegoService.getAllVideojuegos()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body.map { it.toVideojuego() })
                }
            } else{
                return NetworkResult.Error("${response.code()} ${response.message()}")
            }
        }catch (e: Exception){
            return NetworkResult.Error(e.message ?: e.toString())
        }
         return NetworkResult.Error("Hubo un problema al sacar la lista de los videojuegos")
    }

    suspend fun getVideojuego(idVideojuego: Int): NetworkResult<Videojuego>{
        try {
            val response = videojuegoService.getVideojuego(idVideojuego)
            if (response.isSuccessful){
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body.toVideojuego())
                }
            }else{
                return NetworkResult.Error("${response.code()} ${response.message()}")
            }
        }catch (e: Exception){
            return NetworkResult.Error(e.message ?: e.toString())
        }
        return NetworkResult.Error("Hubo un problema al sacar el videojuego con el id proporcionado")
    }

    suspend fun deleteVideojuego(idVideojuego: Int): NetworkResult<Unit>{
        return try {
            val response = videojuegoService.deleteVideojuego(idVideojuego)
            val result = if (response.isSuccessful){
                NetworkResult.Success(Unit)
            } else{
                NetworkResult.Error("${response.code()} ${response.message()}")
            }
            result
        }catch (e: Exception){
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }

    //TODO: HACER EL GET(ID) Y EL DELETE DE VIDEOJUEG0

    //TODO: HACER EL GETALL, EL GET(ID) Y EL DELETE DE PERSONAJE

}