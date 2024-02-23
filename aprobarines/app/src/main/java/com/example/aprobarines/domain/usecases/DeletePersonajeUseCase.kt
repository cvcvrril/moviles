package com.example.aprobarines.domain.usecases

import com.example.aprobarines.data.repository.PersonajeRepository
import javax.inject.Inject

class DeletePersonajeUseCase@Inject constructor(
    var repository : PersonajeRepository
) {

    suspend operator fun invoke(id: Int) = repository.deletePersonaje(id)
}