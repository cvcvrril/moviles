package com.example.flowroomsinesmr.domain.modelo

import com.example.flowroomsinesmr.data.modelo.entity.VideojuegoEntity

data class Videojuego(
    val id: Int,
    val titulo: String,
    val descripcion: String
)

fun Videojuego.toVideojuegoEntity() : VideojuegoEntity = VideojuegoEntity(id, titulo, descripcion)
