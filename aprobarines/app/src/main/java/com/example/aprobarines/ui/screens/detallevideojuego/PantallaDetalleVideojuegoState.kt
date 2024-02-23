package com.example.aprobarines.ui.screens.detallevideojuego

import com.example.aprobarines.domain.modelo.Videojuego

data class PantallaDetalleVideojuegoState(
    val videojuego: Videojuego? = null,
    val isLoading : Boolean = false,
    val error: String? = null
) {
}