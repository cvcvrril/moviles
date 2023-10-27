package com.example.recycledinesmr.domain.usecases

import com.example.recycledinesmr.data.Repository

class DeletePeliculaUseCase {
    operator fun invoke(index: Int): Boolean =
        Repository.deletePelicula(index)

}

