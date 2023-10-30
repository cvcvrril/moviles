package com.example.recycledinesmr.domain.usecases

import com.example.recycledinesmr.data.Repository
import com.example.recycledinesmr.domain.modelo.Pelicula

class UpdatePeliculasUseCase(private val repo : Repository) {
    operator fun invoke(oldPelicula: Pelicula, updatedPelicula: Pelicula) =
         repo.updatePelicula(oldPelicula, updatedPelicula)
}