package com.example.prcticaexamen.data.sources.remote

import com.example.prcticaexamen.data.sources.remote.services.MapaService
import com.example.prcticaexamen.domain.model.Mapa
import com.example.prcticaexamen.utils.NetworkResult
import javax.inject.Inject

class MapaRemoteDataSource @Inject constructor(
    private var service: MapaService
){

    suspend fun getMapas() : NetworkResult<List<Mapa>>{
        try {
            val response = service.getMapas()
            if (response.isSuccessful){
                val body = response.body()
                val mapas = body?.map {
                    Mapa(it.id, it.nombre)
                }
                if (mapas.isNullOrEmpty()){
                    return NetworkResult.Error("La lista está vacía")
                } else{
                    return NetworkResult.Success(mapas)
                }
            }else{
                return NetworkResult.Error(response.message())
            }
        }catch (e : Exception){
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }

}