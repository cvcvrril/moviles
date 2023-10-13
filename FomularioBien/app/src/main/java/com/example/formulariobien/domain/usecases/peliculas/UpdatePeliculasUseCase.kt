package com.example.formulariobien.domain.usecases.peliculas

import com.example.formulariobien.data.Repository
import com.example.formulariobien.domain.modelo.Pelicula

class UpdatePeliculasUseCase {

    fun updatePelicula(index: Int, updatedPelicula: Pelicula): Boolean {
        return Repository.updatePelicula(index, updatedPelicula)
    }

}