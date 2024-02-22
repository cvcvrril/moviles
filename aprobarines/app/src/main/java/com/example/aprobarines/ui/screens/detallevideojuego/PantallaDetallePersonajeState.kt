package com.example.aprobarines.ui.screens.detallevideojuego

import com.example.aprobarines.domain.modelo.Personaje

data class PantallaDetallePersonajeState(
    val personaje: Personaje? = null,
    val error: String? = null
    ) {
}