package com.example.recycledinesmr.domain.usecases

import com.example.recycledinesmr.data.Repository
import com.example.recycledinesmr.domain.modelo.Pelicula

class GetPeliculaUseCase(private val repo:Repository) {
    operator fun invoke( i: Int): Pelicula {
        val peliculas = repo.getLista()
        return peliculas[i]
    }
}