package com.example.unapantallainesmr.ui.main

sealed class MainEvent {

    object GetSeries : MainEvent()
    object Error : MainEvent()


}