package com.example.formulariobien.domain.usecases.personas

import com.example.formulariobien.data.Repository
import com.example.formulariobien.domain.modelo.Pelicula

class AddPeliculasUseCase {

    operator fun invoke(pelicula: Pelicula): Unit =
        Repository.addPelicula(pelicula)

}