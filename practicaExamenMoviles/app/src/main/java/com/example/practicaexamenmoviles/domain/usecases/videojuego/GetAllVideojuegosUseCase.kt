package com.example.practicaexamenmoviles.domain.usecases.videojuego

import com.example.practicaexamenmoviles.data.repositories.VideojuegoRepository
import javax.inject.Inject

class GetAllVideojuegosUseCase @Inject constructor(var repository: VideojuegoRepository) {
    suspend operator fun invoke() = repository.getVideojuegos()
}