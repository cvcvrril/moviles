package com.example.aprobarines.domain.usecases

import com.example.aprobarines.data.repository.VideojuegoRepository
import javax.inject.Inject

class GetVideojuegosUseCase  @Inject constructor(
    var repository : VideojuegoRepository
){
    suspend operator fun invoke() = repository.getVideojuegos()
}