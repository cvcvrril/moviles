package com.example.prcticaexamen.ui.screens.listamapa

sealed class PantallaListaMapaEvent {

    object GetMapas : PantallaListaMapaEvent()
    object ErrorVisto : PantallaListaMapaEvent()
    class DeletePersonaje(val id: Int) : PantallaListaMapaEvent()

}