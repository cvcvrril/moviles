package com.example.aprobarines.ui.screens.listapersonaje

import com.example.aprobarines.domain.modelo.Personaje

data class PantallaListaPersonajeState(val personajes: List<Personaje> = emptyList(),
                                       val isLoading : Boolean = false,
                                       val error: String? = null)
