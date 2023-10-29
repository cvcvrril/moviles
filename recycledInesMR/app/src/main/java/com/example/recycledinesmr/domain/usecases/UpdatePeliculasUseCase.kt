package com.example.recycledinesmr.domain.usecases

import com.example.recycledinesmr.data.Repository
import com.example.recycledinesmr.domain.modelo.Pelicula
import java.io.InputStream

class UpdatePeliculasUseCase(private val file: InputStream) {

    operator fun invoke(index: Int, updatedPelicula: Pelicula): Boolean =
         Repository(file).updatePelicula(index, updatedPelicula)


}