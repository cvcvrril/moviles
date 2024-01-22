package com.example.unapantallainesmr.domain

import java.time.LocalDate

data class Serie(
    val id: Int,
    val titulo: String,
    val puntuacion: Float,
    val favorito: Boolean,
    val image: String
)