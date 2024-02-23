package com.example.aprobarines.ui.screens.pantallaaddpersonaje

sealed class PantallaAddPersonajeEvent {

    class AddPersonaje(val nombre: String) : PantallaAddPersonajeEvent()
    object ErrorVisto : PantallaAddPersonajeEvent()

}