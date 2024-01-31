package com.example.unapantallainesmr.domain.modelo

data class Serie(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val puntuacion: Float,
    val favorito: Boolean,
)