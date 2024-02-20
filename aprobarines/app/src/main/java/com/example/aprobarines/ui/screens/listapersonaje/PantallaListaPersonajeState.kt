package com.example.aprobarines.ui.screens.listapersonaje

import com.example.aprobarines.domain.modelo.Videojuego

data class PantallaListaPersonajeState(val videojuegos: List<Videojuego> = emptyList(),
                                       val isLoading : Boolean = false,
                                       val error: String? = null)
