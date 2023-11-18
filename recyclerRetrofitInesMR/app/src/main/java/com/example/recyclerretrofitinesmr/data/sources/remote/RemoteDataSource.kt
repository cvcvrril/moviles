package com.example.recyclerretrofitinesmr.data.sources.remote

import android.util.Log
import com.example.recyclerretrofitinesmr.data.model.BaseApiResponse
import com.example.recyclerretrofitinesmr.data.model.toDirector
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val directorService: DirectorService):
    BaseApiResponse() {

    suspend fun getAllDirector(): NetworkResult<List<Director>>{
        try {
            val response = directorService.getAllDirector()
            if (response.isSuccessful){
                response.body()?.let {
                    val directores = it.map{directorResponse ->
                        directorResponse.toDirector()
                    }
                    Log.d("Directores (RemoteDataSource)", "Directores: ${directores}")
                    return NetworkResult.Success(directores)
                }
                return error("No hay datos")
            }
            return error("Ha habido un error al conseguir la información")
        } catch (e: Exception){
            return error(e.message?: e.toString())
        }
    }

    suspend fun getDirector(id: String): NetworkResult<Director>{
        try {
            return error("Ha habido un error al conseguir la información")
        }catch (e:Exception){
            return error(e.message?: e.toString())
        }
    }
}