package com.example.formulariobien.ui.pantallaMain

import com.example.formulariobien.domain.modelo.Pelicula



data class MainState(
    val pelicula: Pelicula = Pelicula(titulo = Constantes.VACIO, director = Constantes.VACIO, fecha = null, cast = Constantes.VACIO, estrellas = 0.00f, recaudado = 0.00f, generoComedia = false, generoTerror = false, generoRomance = false, generoTragedia = false, clasificacionEdad = Constantes.VACIO),
    val error: String? = null,
    val indiceActual: Int = 0
)