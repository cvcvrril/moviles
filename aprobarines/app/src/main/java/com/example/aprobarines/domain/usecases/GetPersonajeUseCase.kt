package com.example.aprobarines.domain.usecases

import com.example.aprobarines.data.repository.PersonajeRepository
import javax.inject.Inject

class GetPersonajeUseCase @Inject constructor(
    var repository : PersonajeRepository
){

    suspend operator fun invoke(id: Int) = repository.getPersonaje(id)
}