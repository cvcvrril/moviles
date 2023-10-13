package com.example.formulariobien.domain.usecases.peliculas

import com.example.formulariobien.data.Repository

class DeletePeliculaUseCase {
    operator fun invoke(index: Int): Boolean =
        Repository.deletePelicula(index)

}