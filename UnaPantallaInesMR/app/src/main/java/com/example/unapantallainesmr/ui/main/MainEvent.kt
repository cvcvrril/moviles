package com.example.unapantallainesmr.ui.main

sealed class MainEvent {
    class ChangeTexto(val texto: String) : MainEvent()
    class ChangeMode(val mode: Boolean?) : MainEvent()
    class GetSerie(val id: Int) : MainEvent()
    object Error : MainEvent()
    object GetSeries : MainEvent()


}