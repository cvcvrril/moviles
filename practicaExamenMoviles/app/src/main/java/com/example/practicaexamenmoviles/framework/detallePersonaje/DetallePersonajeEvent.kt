package com.example.practicaexamenmoviles.framework.detallePersonaje

import com.example.practicaexamenmoviles.framework.personaje.PersonajeEvent

sealed class DetallePersonajeEvent {
    class GetPersonaje(val idPersonaje: Int) : DetallePersonajeEvent()

}