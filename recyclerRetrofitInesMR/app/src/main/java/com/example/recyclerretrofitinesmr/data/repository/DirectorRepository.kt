package com.example.recyclerretrofitinesmr.data.repository

import android.util.Log
import com.example.recyclerretrofitinesmr.data.model.toDirector
import com.example.recyclerretrofitinesmr.data.sources.remote.DirectorService
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.utils.Constants
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
class DirectorRepository @Inject constructor(
    private val directorService: DirectorService
) {

    suspend fun getAllDirector(): NetworkResult<List<Director>> {
        try {
            val response = directorService.getAllDirector()
            if (response.isSuccessful) {
                response.body()?.let {
                    val directores = it.map { directorResponse ->
                        directorResponse.toDirector()
                    }
                    return NetworkResult.Success(directores)
                }
                return error(Constants.NO_HAY_DATOS)
            }
            return error(Constants.HA_HABIDO_UN_ERROR_AL_CONSEGUIR_LA_INFORMACION)
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    suspend fun getDirector(idParam: String): NetworkResult<Director> {
        try {
            val id: Int = idParam.toInt()
            val response = directorService.getDirector(id)
            if (response.isSuccessful) {
                val directorResponse = response.body()
                if (directorResponse != null) {
                    val director = directorResponse.toDirector()
                    return NetworkResult.Success(director)
                } else {
                    return NetworkResult.Error(Constants.RESPUESTA_NULA_DEL_SERVIDOR)
                }
            }
            return error(Constants.HA_HABIDO_UN_ERROR_AL_CONSEGUIR_LA_INFORMACION)
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        } catch (e: NumberFormatException) {
            return NetworkResult.Error(Constants.EL_PARAMETRO_ID_NO_ES_UN_NUMERO_VALIDO)
        }
    }

    suspend fun deleteDirector(idParam: String): NetworkResult<Unit> {
        try {
            val id: Int = idParam.toInt()
            val response = directorService.deleteDirector(id)

            return if (response.isSuccessful) {
                NetworkResult.Success(Unit)
            } else {
                NetworkResult.Error(Constants.ERROR_ELIMINAR_DIRECTOR)
            }
        } catch (e: NumberFormatException) {
            return NetworkResult.Error(Constants.EL_PARAMETRO_ID_NO_ES_UN_NUMERO_VALIDO)
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }

    }
}