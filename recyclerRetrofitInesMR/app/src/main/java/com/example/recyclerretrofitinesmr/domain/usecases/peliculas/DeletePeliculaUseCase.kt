package com.example.recyclerretrofitinesmr.domain.usecases.peliculas

import android.util.Log
import com.example.recyclerretrofitinesmr.data.repository.PeliculaRepository
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@ActivityRetainedScoped
class DeletePeliculaUseCase@Inject constructor(
    private val peliculaRepository: PeliculaRepository
) {

    suspend fun deletePelicula(id:String): NetworkResult<Unit> {
        return withContext(Dispatchers.IO)
        { peliculaRepository.deletePelicula(id) }
        Log.d("Directores (DirectorRepository)", "Directores: ${peliculaRepository.getAllPeliculas()}")
    }
}