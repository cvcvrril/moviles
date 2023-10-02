package com.example.formulariobien.domain.usecases.personas

import com.example.formulariobien.data.Repository
import com.example.formulariobien.domain.modelo.Persona

class AddPersonaUseCase {

    operator fun invoke(persona: Persona) =
        Repository.addPersona(persona)



}