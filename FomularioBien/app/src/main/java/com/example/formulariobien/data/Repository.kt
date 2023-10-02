package com.example.formulariobien.data

import com.example.formulariobien.domain.modelo.Persona

object Repository {

    private val personas = mutableListOf<Persona>()

    init {
        personas.add(Persona("Juan"))
    }

    private val mapPersonas = mutableMapOf<String, Persona>()

    fun addPersona(persona: Persona) =
        personas.add(persona)

    fun getPersonas(): List<Persona>{
        return personas
    }

}