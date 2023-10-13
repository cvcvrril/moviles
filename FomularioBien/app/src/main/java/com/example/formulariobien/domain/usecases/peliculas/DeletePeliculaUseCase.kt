package com.example.formulariobien.domain.usecases.peliculas

import com.example.formulariobien.data.Repository

class DeletePeliculaUseCase {
    fun deletePelicula(index: Int): Boolean {
        return Repository.deletePelicula(index)
    }
}