package com.example.flowroomsinesmr.domain.usecases.jugador

import com.example.flowroomsinesmr.data.repository.JugadorRepository
import javax.inject.Inject

class GetAllJugadoresUseCase @Inject constructor(var repository: JugadorRepository) {
    suspend operator fun invoke() = repository.getAllJugadores()

}