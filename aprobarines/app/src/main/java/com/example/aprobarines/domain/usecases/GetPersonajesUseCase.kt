package com.example.aprobarines.domain.usecases

import com.example.aprobarines.data.repository.PersonajeRepository
import com.example.aprobarines.data.repository.VideojuegoRepository
import javax.inject.Inject

class GetPersonajesUseCase @Inject constructor(
    var repository : PersonajeRepository
) {

    suspend operator fun invoke() = repository.getPersonajes()

}