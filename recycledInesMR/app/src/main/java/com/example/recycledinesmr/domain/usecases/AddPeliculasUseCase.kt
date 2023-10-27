package com.example.recycledinesmr.domain.usecases

import com.example.recycledinesmr.data.Repository
import com.example.recycledinesmr.domain.modelo.Pelicula

public class AddPeliculasUseCase {

    operator fun invoke(pelicula: Pelicula) =
        Repository.addPelicula(pelicula)

}