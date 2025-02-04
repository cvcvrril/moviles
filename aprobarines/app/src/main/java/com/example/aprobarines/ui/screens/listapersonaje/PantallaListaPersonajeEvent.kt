package com.example.aprobarines.ui.screens.listapersonaje

sealed class PantallaListaPersonajeEvent {
    object GetPersonajes : PantallaListaPersonajeEvent()
    object ErrorVisto : PantallaListaPersonajeEvent()
    class DeletePersonaje(val id: Int) : PantallaListaPersonajeEvent()

}