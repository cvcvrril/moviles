package com.example.recyclerretrofitinesmr.data.repository

import android.util.Log
import com.example.recyclerretrofitinesmr.data.model.toDirector
import com.example.recyclerretrofitinesmr.data.sources.remote.DirectorService
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import timber.log.Timber
import javax.inject.Inject

@ActivityRetainedScoped
class PeliculaRepository @Inject constructor(
    private val directorService: DirectorService
)  {

    suspend fun getAllPeliculas(): NetworkResult<List<Director>> {
        try {
            val response = directorService.getAllDirector()
            if (response.isSuccessful) {
                response.body()?.let {
                    val directores = it.map { directorResponse ->
                        directorResponse.toDirector()
                    }
                    Timber.tag("Directores (RemoteDataSource)").d("Directores: " + directores)
                    return NetworkResult.Success(directores)
                }
                return error("No hay datos")
            }
            return error("Ha habido un error al conseguir la información")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }


}