package com.example.formulariobien.domain.usecases.peliculas

import com.example.recycledinesmr.data.Repository
import com.example.recycledinesmr.domain.modelo.Pelicula

class GetPeliculaUseCase {
    operator fun invoke( i: Int): Pelicula {
        val peliculas = Repository.getPelicula()
        return peliculas.get(i)
    }
}