package com.example.recyclerretrofitinesmr.ui.detailPelicula

import com.example.recyclerretrofitinesmr.domain.Pelicula

data class DetailPeliculaState (
    val pelicula: Pelicula? = null,
    val error: String? = null
)