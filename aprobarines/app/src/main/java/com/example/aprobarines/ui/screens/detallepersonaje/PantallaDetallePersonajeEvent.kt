package com.example.aprobarines.ui.screens.detallepersonaje

sealed class PantallaDetallePersonajeEvent {

    class GetPersonaje(val id: Int) : PantallaDetallePersonajeEvent()
    object ErrorVisto : PantallaDetallePersonajeEvent()

}