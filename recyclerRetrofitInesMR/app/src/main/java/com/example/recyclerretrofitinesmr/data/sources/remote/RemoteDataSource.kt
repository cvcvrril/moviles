package com.example.recyclerretrofitinesmr.data.sources.remote

import com.example.recyclerretrofitinesmr.data.model.BaseApiResponse
import com.example.recyclerretrofitinesmr.data.model.DirectorResponse
import com.example.recyclerretrofitinesmr.data.model.toDirector
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.utils.NetworkResultt
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val directorService: DirectorService):
    BaseApiResponse() {

    suspend fun getAllDirector(): NetworkResultt<List<Director>>{
        try {
            val response = directorService.getAllDirector()
            if (response.isSuccessful){
                val body = response.body()
                body?.let {
                    val directores = it.map{directorResponse ->
                        directorResponse.toDirector()
                    }
                    return NetworkResultt.Success(directores)
                }
                return error("No hay datos")
            }
            return error("Ha habido un error al conseguir la información")
        } catch (e: Exception){
            return error(e.message?: e.toString())
        }
    }

    suspend fun getDirector(id: String): NetworkResultt<Director>{
        try {
            return error("Ha habido un error al conseguir la información")
        }catch (e:Exception){
            return error(e.message?: e.toString())
        }
    }
}