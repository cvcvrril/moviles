package com.example.aprobarines.ui.screens.detallevideojuego

sealed class PantallaDetallePersonajeEvent {

    class GetPersonaje(val id: Int) : PantallaDetallePersonajeEvent()
    object ErrorVisto : PantallaDetallePersonajeEvent()

}