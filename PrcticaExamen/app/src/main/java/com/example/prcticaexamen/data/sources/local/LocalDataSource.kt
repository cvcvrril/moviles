package com.example.prcticaexamen.data.sources.local

import com.example.prcticaexamen.data.model.toVideojuego
import com.example.prcticaexamen.domain.model.Videojuego
import com.example.prcticaexamen.utils.ServiceResult
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val videojuegosDao: VideojuegosDao
) {

    suspend fun getAllVideojuegosRoom() : ServiceResult<List<Videojuego>>{
       try {
            val response = videojuegosDao.getAllVideojuegosRoom()
           if (response.isNullOrEmpty()){
               return ServiceResult.Error("La lista está vacía")
           }else{
               val videojuegos = response.map {
                    it.toVideojuego()
               }
                return ServiceResult.Success(videojuegos)
           }
       }catch (e: Exception){
           return ServiceResult.Error(e.message.toString())
       }
    }


}