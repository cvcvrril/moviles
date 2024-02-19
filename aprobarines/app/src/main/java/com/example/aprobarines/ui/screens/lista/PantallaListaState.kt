package com.example.composefullequip.ui.screens.lista

import com.example.aprobarines.domain.modelo.Videojuego

data class PantallaListaState(val videojuegos: List<Videojuego> = emptyList(),
                              val isLoading : Boolean = false,
                              val error: String? = null)
