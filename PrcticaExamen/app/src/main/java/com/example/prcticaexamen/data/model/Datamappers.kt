package com.example.prcticaexamen.data.model

import com.example.prcticaexamen.domain.model.Videojuego

fun VideojuegoEntity.toVideojuego() : Videojuego{
    return Videojuego(id, titulo, descripcion)
}

fun Videojuego.toVideojuegoEntity() : VideojuegoEntity{
    return VideojuegoEntity(id, titulo, descripcion)
}