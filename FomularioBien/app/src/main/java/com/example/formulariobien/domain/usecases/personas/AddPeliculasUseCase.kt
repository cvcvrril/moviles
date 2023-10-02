package com.example.formulariobien.domain.usecases.personas

import com.example.formulariobien.data.Repository
import com.example.formulariobien.domain.modelo.Peliculas

class AddPeliculasUseCase {

    operator fun invoke(peliculas: Peliculas) =
        Repository.addPelicula(peliculas)



}