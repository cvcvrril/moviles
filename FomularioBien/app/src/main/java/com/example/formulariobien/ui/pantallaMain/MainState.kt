package com.example.formulariobien.ui.pantallaMain

import com.example.formulariobien.domain.modelo.Pelicula

data class MainState(
    val pelicula: Pelicula = Pelicula(titulo = "", director = "", fecha = null, cast = ""),
    val error: String? = null
)