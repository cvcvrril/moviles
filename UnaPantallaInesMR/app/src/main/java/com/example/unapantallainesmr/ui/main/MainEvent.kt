package com.example.unapantallainesmr.ui.main

sealed class MainEvent {
    class ChangeTexto(val texto: String) : MainEvent();
    object GetSeries : MainEvent()
    object Error : MainEvent()


}