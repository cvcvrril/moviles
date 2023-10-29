package com.example.recycledinesmr.domain.usecases

import com.example.recycledinesmr.data.Repository
import com.example.recycledinesmr.domain.modelo.Pelicula
import java.io.InputStream

class GetListaUseCase(private val file: InputStream) {

    operator fun invoke(): List<Pelicula>{
        return Repository(file).getLista()
    }
}