package com.example.practicaexamenmoviles.framework.detallePersonaje

import com.example.practicaexamenmoviles.domain.model.Personaje

data class DetallePersonajeState (
    val personaje: Personaje? = null,
    val error: String? = null

)


