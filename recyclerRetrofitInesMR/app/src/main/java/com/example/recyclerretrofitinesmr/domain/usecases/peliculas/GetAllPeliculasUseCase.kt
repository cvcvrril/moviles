package com.example.recyclerretrofitinesmr.domain.usecases.peliculas

import android.util.Log
import com.example.recyclerretrofitinesmr.data.repository.DirectorRepository
import com.example.recyclerretrofitinesmr.data.repository.PeliculaRepository
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllPeliculasUseCase@Inject constructor(
    private val peliculaRepository: PeliculaRepository
) {
    suspend fun getAllPeliculas(): NetworkResult<List<Director>> {
        return withContext(Dispatchers.IO)
        { peliculaRepository.getAllPeliculas() }
        Log.d("Directores (DirectorRepository)", "Directores: ${peliculaRepository.getAllPeliculas()}")
    }


}