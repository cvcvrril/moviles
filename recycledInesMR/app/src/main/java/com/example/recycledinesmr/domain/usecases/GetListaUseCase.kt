package com.example.recycledinesmr.domain.usecases

import com.example.recycledinesmr.data.Repository
import com.example.recycledinesmr.domain.modelo.Pelicula

class GetListaUseCase(private val repo: Repository) {

    operator fun invoke(): List<Pelicula>{
        return repo.getLista()
    }
}