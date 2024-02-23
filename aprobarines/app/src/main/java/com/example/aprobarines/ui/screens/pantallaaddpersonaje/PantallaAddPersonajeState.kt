package com.example.aprobarines.ui.screens.pantallaaddpersonaje

import com.example.aprobarines.domain.modelo.Personaje

data class PantallaAddPersonajeState(
    val personaje: Personaje? = null,
    val isLoading : Boolean = false,
    val error: String? = null
) {
}