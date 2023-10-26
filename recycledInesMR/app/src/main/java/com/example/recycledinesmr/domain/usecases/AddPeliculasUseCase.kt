package com.example.formulariobien.domain.usecases.peliculas

import com.example.recycledinesmr.data.Repository
import com.example.recycledinesmr.domain.modelo.Pelicula

class AddPeliculasUseCase {

    operator fun invoke(pelicula: Pelicula) =
        Repository.addPelicula(pelicula)

}