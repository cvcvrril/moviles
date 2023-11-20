package com.example.recyclerretrofitinesmr.domain.usecases.peliculas

import android.util.Log
import com.example.recyclerretrofitinesmr.data.repository.PeliculaRepository
import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllPeliculasIdDirectorUseCase @Inject constructor(
    private val peliculaRepository: PeliculaRepository
) {
    suspend fun getAllPeliculasIdDirector(idDirector: Int): NetworkResult<List<Pelicula>>{
        return withContext(Dispatchers.IO)
        { peliculaRepository.getPeliculasIdDirector(idDirector) }
        Log.d("Directores (DirectorRepository)", "Directores: ${peliculaRepository.getAllPeliculas()}")
    }
}