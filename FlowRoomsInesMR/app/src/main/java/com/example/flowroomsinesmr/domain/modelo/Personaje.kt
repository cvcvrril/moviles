package com.example.flowroomsinesmr.domain.modelo

import com.example.flowroomsinesmr.data.modelo.response.PersonajeResponse

data class Personaje (
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val idVideojuego: Int
)

//fun Personaje.toPersonajeResponse() : PersonajeResponse = PersonajeResponse()
