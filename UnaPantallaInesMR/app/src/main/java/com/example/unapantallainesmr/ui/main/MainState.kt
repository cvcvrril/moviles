package com.example.unapantallainesmr.ui.main

import com.example.unapantallainesmr.domain.modelo.Serie

data class MainState (
    //TODO: METER UN BOOLEANO PARA CAMBIAR EL ESTADO DE EDICIÓN A VISUALIZACIÓN
    //TODO: METER LA LISTA DE OBJETOS
    val editMode: Boolean = false,
    val series: List<Serie> = emptyList(),
    val error: String? = null,

)



