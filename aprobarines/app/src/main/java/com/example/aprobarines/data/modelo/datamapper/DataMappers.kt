package com.example.aprobarines.data.modelo.datamapper

import com.example.aprobarines.domain.modelo.Videojuego

fun Videojuego.toVideojuego() : Videojuego{
    return Videojuego(id, titulo, descripcion)
}