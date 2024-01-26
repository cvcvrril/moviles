package com.example.unapantallainesmr.ui.main

sealed class MainEvent {
    class ChangeTexto(val texto: String) : MainEvent()
    object Error : MainEvent()
    object GetSeries : MainEvent()

}