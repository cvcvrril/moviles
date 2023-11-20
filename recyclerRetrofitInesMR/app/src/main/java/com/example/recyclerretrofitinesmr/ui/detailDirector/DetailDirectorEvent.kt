package com.example.recyclerretrofitinesmr.ui.detailDirector

import com.example.recyclerretrofitinesmr.ui.main.MainEvent

sealed class DetailDirectorEvent {

    class GetAllPeliculasIdDirector(val idDirector: String) : DetailDirectorEvent()

}