package com.example.composefullequip.ui.screens.lista

sealed class PantallaListaEvent {
    object GetVideojuegos : PantallaListaEvent()
    object ErrorVisto : PantallaListaEvent()

}