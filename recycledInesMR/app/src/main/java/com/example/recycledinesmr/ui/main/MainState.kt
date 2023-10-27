package com.example.recycledinesmr.ui.main

import com.example.recycledinesmr.domain.modelo.Pelicula
import com.example.recycledinesmr.ui.Constantes


data class MainState(
    val pelicula: Pelicula = Pelicula(titulo = Constantes.VACIO, director = Constantes.VACIO, fecha = null, estrellas = 0.00f, clasificacionEdad = Constantes.VACIO),
    val error: String? = null,
    val indiceActual: Int = 0
)