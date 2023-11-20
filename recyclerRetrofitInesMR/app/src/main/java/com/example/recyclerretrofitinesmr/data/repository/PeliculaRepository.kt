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
)  {

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


}