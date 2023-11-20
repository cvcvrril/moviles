package com.example.recyclerretrofitinesmr.domain.usecases.peliculas

import com.example.recyclerretrofitinesmr.data.repository.PeliculaRepository
import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllPeliculasUseCase@Inject constructor(
    private val peliculaRepository: PeliculaRepository
) {
    suspend fun getAllPeliculas(): NetworkResult<List<Pelicula>> {
        return withContext(Dispatchers.IO)
        { peliculaRepository.getAllPeliculas() }
    }


}