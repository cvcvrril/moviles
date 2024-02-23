package com.example.aprobarines.domain.usecases

import com.example.aprobarines.data.repository.PersonajeRepository
import javax.inject.Inject

class AddPersonajeUseCase @Inject constructor(
    var repository : PersonajeRepository
) {

    suspend operator fun invoke(nombre: String) = repository.addPersonaje(nombre)

}