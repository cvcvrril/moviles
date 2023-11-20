package com.example.recyclerretrofitinesmr.data.repository

import com.example.recyclerretrofitinesmr.data.model.toPelicula
import com.example.recyclerretrofitinesmr.data.sources.remote.PeliculaService
import com.example.recyclerretrofitinesmr.domain.Pelicula
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
                    Timber.tag("Peliculas (RemoteDataSource)").d("Directores: " + peliculas)
                    return NetworkResult.Success(peliculas)
                }
                return error("No hay datos")
            }
            return error("Ha habido un error al conseguir la información")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    suspend fun getPeliculasIdDirector(idDirectorParam: Int): NetworkResult<List<Pelicula>> {
        try {
            val response = peliculaService.getPeliculasIdDirector(idDirectorParam)
            Timber.d("Peliculas(Repository)","IdDirector: ${idDirectorParam}")
            if (response.isSuccessful) {
                val peliculaResponse = response.body()
                if (peliculaResponse != null) {
                    val peliculasDirector =
                        peliculaResponse.map { peliculaResponse -> peliculaResponse.toPelicula() }
                    return NetworkResult.Success(peliculasDirector)
                } else {
                    return NetworkResult.Error("Respuesta nula del servidor")
                }
            } else {
                return NetworkResult.Error("Error en la respuesta del servidor: ${response.code()}")
            }
        } catch (e: Exception) {
            return NetworkResult.Error("Error: ${e.message}")
        }
    }

    suspend fun deletePelicula(idParam: String): NetworkResult<Unit> {
        try {
            val id: Int = idParam.toInt()
            val response = peliculaService.deletePelicula(id)

            return if (response.isSuccessful) {
                NetworkResult.Success(Unit)
            } else {
                NetworkResult.Error("Ha habido un error al eliminar la película: ${response.code()}")
            }
        } catch (e: NumberFormatException) {
            return NetworkResult.Error("El parámetro id no es un número válido")
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }

    }

}