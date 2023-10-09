package com.example.formulariobien.domain.usecases.peliculas

import com.example.formulariobien.data.Repository
import com.example.formulariobien.domain.modelo.Pelicula

class GetPeliculaUseCase {
    operator fun invoke( i: Int): Pelicula? {
        val peliculas = Repository.getPelicula()
        return peliculas.firstOrNull()
    }
}