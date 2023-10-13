package com.example.formulariobien.domain.usecases.peliculas

import com.example.formulariobien.data.Repository
import com.example.formulariobien.domain.modelo.Pelicula

class UpdatePeliculasUseCase {

    operator fun invoke(index: Int, updatedPelicula: Pelicula): Boolean =
         Repository.updatePelicula(index, updatedPelicula)


}