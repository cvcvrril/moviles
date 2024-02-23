package com.example.aprobarines.ui.screens.detallevideojuego

sealed class PantallaDetalleVideojuegoEvent {

    class GetVideojuego(val id: Int) : PantallaDetalleVideojuegoEvent()
    object ErrorVisto : PantallaDetalleVideojuegoEvent()

}