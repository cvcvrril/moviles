package com.example.aprobarines.ui.screens.listapersonaje

sealed class PantallaListaPersonajeEvent {
    object GetVideojuegos : PantallaListaPersonajeEvent()
    object ErrorVisto : PantallaListaPersonajeEvent()

}