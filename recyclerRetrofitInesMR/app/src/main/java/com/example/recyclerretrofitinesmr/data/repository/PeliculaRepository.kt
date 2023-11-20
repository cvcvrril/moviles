package com.example.recyclerretrofitinesmr.data.repository

import com.example.recyclerretrofitinesmr.data.model.toPelicula
import com.example.recyclerretrofitinesmr.data.sources.remote.PeliculaService
import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.example.recyclerretrofitinesmr.utils.Constants
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import timber.log.Timber
import javax.inject.Inject



@ActivityRetainedScoped
class PeliculaRepository @Inject constructor(
    private val peliculaService: PeliculaService
) {

    suspend fun getAllPeliculas(): NetworkResult<List<Pelicula>> {
        try {
            val response = peliculaService.getAllPeliculas()
            if (response.isSuccessful) {
                response.body()?.let {
                    val peliculas = it.map { peliculaResponse ->
                        peliculaResponse.toPelicula()
                    }
                    return NetworkResult.Success(peliculas)
                }
                return error(Constants.NO_HAY_DATOS)
            }
            return error(Constants.HA_HABIDO_UN_ERROR_AL_CONSEGUIR_LA_INFORMACION)
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    suspend fun getPeliculasIdDirector(idDirectorParam: Int): NetworkResult<List<Pelicula>> {
        try {
            val response = peliculaService.getPeliculasIdDirector(idDirectorParam)
            if (response.isSuccessful) {
                val peliculaResponse = response.body()
                if (peliculaResponse != null) {
                    val peliculasDirector =
                        peliculaResponse.map { peliculaResponse -> peliculaResponse.toPelicula() }
                    return NetworkResult.Success(peliculasDirector)
                } else {
                    return NetworkResult.Error(Constants.RESPUESTA_NULA_DEL_SERVIDOR)
                }
            } else {
                return NetworkResult.Error(Constants.ERROR_SERVIDOR)
            }
        } catch (e: Exception) {
            return NetworkResult.Error(Constants.HA_HABIDO_UN_ERROR_AL_CONSEGUIR_LA_INFORMACION)
        }
    }

    suspend fun deletePelicula(idParam: String): NetworkResult<Unit> {
        try {
            val id: Int = idParam.toInt()
            val response = peliculaService.deletePelicula(id)

            return if (response.isSuccessful) {
                NetworkResult.Success(Unit)
            } else {
                NetworkResult.Error(Constants.ERROR_ELIMINAR_PELI)
            }
        } catch (e: NumberFormatException) {
            return NetworkResult.Error(Constants.EL_PARAMETRO_ID_NO_ES_UN_NUMERO_VALIDO)
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }

    }

}