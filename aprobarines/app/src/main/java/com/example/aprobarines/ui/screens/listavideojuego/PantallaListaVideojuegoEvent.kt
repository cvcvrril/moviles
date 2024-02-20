package com.example.composefullequip.ui.screens.lista

sealed class PantallaListaVideojuegoEvent {
    object GetVideojuegos : PantallaListaVideojuegoEvent()
    object ErrorVisto : PantallaListaVideojuegoEvent()

}