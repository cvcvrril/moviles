package com.example.formulariobien.domain.usecases.peliculas

import com.example.recycledinesmr.data.Repository
import com.example.recycledinesmr.domain.modelo.Pelicula

class UpdatePeliculasUseCase {

    operator fun invoke(index: Int, updatedPelicula: Pelicula): Boolean =
         Repository.updatePelicula(index, updatedPelicula)


}