package com.example.recyclerretrofitinesmr.ui.main

import com.example.recyclerretrofitinesmr.domain.Director

sealed class MainEvent {

    object GetAllDirectores : MainEvent()
    class GetDirector(val id: String) : MainEvent()
    class DeleteDirector (val director: Director) : MainEvent()
    class SeleccionaDirector(val director: Director) : MainEvent()
    object ErrorVisto : MainEvent()
    class DeleteDirectoresSeleccionados() : MainEvent()
    object ResetSelectMode: MainEvent()
    object StartSelectMode: MainEvent()
}