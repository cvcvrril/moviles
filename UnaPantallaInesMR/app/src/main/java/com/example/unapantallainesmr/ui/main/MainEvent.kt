package com.example.unapantallainesmr.ui.main

import com.example.unapantallainesmr.domain.modelo.Serie

sealed class MainEvent {
    class ChangeTexto(val texto: String) : MainEvent()
    class ChangeMode(val mode: Boolean?) : MainEvent()
    class GetSerie(val id: Int) : MainEvent()
    class AddSerie(val serie: Serie) : MainEvent()
    class UpdateSerie(val serie: Serie) : MainEvent()
    class DeleteSerie(val serie: Serie) : MainEvent()
    object Error : MainEvent()
    object GetSeries : MainEvent()


}