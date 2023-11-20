package com.example.recyclerretrofitinesmr.ui.detailDirector

import com.example.recyclerretrofitinesmr.domain.Pelicula

sealed class DetailDirectorEvent {

    class GetAllPeliculasIdDirector(val idDirector: Int) : DetailDirectorEvent()
    class DeletePelicula (val pelicula: Pelicula) : DetailDirectorEvent()

}