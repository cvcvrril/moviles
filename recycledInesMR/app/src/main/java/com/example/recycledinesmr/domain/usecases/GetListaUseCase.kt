package com.example.recycledinesmr.domain.usecases

import com.example.recycledinesmr.data.Repository

class GetListaUseCase {

    operator fun invoke(){
        val listaPeliculas = Repository.getPelicula()
    }
}