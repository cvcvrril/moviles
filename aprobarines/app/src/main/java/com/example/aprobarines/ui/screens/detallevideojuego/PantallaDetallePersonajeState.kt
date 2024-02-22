package com.example.aprobarines.ui.screens.detallevideojuego

import com.example.aprobarines.domain.modelo.Personaje

data class PantallaDetallePersonajeState(
    val personaje: Personaje? = null,
    val isLoading : Boolean = false,
    val error: String? = null
    ) {
}