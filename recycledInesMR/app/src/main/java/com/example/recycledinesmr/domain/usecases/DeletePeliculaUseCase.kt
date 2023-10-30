package com.example.recycledinesmr.domain.usecases

import com.example.recycledinesmr.data.Repository

class DeletePeliculaUseCase(private val repo:Repository) {
    operator fun invoke(index: Int): Boolean =
        repo.deletePelicula(index)

}

