package com.example.flowroomsinesmr.domain.usecases.videojuego

import com.example.flowroomsinesmr.data.repository.VideojuegoRepository
import com.example.flowroomsinesmr.domain.modelo.Videojuego
import com.example.flowroomsinesmr.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllVideojuegosUseCase @Inject constructor(var repository: VideojuegoRepository) {
    suspend operator fun invoke(): Flow<NetworkResult<List<Videojuego>>> = repository.getAllVideojuegos()

}