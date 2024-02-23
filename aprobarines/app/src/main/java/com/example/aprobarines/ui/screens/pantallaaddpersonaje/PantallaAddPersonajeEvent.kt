package com.example.aprobarines.ui.screens.pantallaaddpersonaje

sealed class PantallaAddPersonajeEvent {

    class IntroducedNombre(val nombre:String) : PantallaAddPersonajeEvent()
    class AddPersonaje(val nombre: String) : PantallaAddPersonajeEvent()
    object ErrorVisto : PantallaAddPersonajeEvent()

}