package com.example.recycledinesmr.domain.usecases

import com.example.recycledinesmr.data.Repository
import com.example.recycledinesmr.domain.modelo.Pelicula
import java.io.InputStream

class UpdatePeliculasUseCase(private val repo : Repository) {

    operator fun invoke(index: Int, updatedPelicula: Pelicula): Boolean =
         repo.updatePelicula(index, updatedPelicula)


}