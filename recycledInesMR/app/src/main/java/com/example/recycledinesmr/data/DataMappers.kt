package com.example.recycledinesmr.data

import com.example.recycledinesmr.domain.modelo.Pelicula

fun Pelicula.toPelicula() : Pelicula = Pelicula(titulo, director, fecha, estrellas, generoComedia, generoTerror, generoRomance, generoTragedia)