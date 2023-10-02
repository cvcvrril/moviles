package com.example.formulariobien.data

import com.example.formulariobien.domain.modelo.Persona

class Repository {

    private val personas = mutableListOf<Persona>()

    init {
        personas.add(Persona("Juan"))
    }

    private val mapPersonas = mutableMapOf<String, Persona>()

    fun add(persona: Persona) = personas.add(persona)

    fun getPersonas(): List<Persona>{
        return personas
    }

}