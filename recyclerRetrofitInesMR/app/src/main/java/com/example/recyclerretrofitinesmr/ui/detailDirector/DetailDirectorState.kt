package com.example.recyclerretrofitinesmr.ui.detailDirector

import com.example.recyclerretrofitinesmr.domain.Pelicula

data class DetailDirectorState (
    val peliculas: List<Pelicula> = emptyList(),
    val error: String? = null
)
