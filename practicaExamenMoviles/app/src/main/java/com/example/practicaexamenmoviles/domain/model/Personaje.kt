package com.example.practicaexamenmoviles.domain.model

import com.example.practicaexamenmoviles.data.model.PersonajeResponse

data class Personaje (
    val id: Int,
    val name: String,
    val idVideojuego: Int,
    val imagen: String)
fun Personaje.toPersonajeResponse(): PersonajeResponse = PersonajeResponse(id, name, idVideojuego, imagen)