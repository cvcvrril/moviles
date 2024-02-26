package com.example.prcticaexamen.ui.screens.listamapa

import com.example.prcticaexamen.domain.model.Mapa

data class PantallaListaMapaState (
    val mapas: List<Mapa> = emptyList(),
    val isLoading : Boolean = false,
    val error: String? = null
)