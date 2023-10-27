package com.example.recycledinesmr.data

import com.example.recycledinesmr.data.modelo.PeliculaJson
import com.example.recycledinesmr.domain.modelo.Pelicula

fun PeliculaJson.toPelicula() : Pelicula = Pelicula(titulo, director, fecha, estrellas, clasificacionEdad)