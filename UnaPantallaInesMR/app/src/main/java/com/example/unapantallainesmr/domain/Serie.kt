package com.example.unapantallainesmr.domain

import java.time.LocalDate

data class Serie(
    val id: Int,
    val titulo: String,
    val fecha: LocalDate,
    val estrellas: Float,
    val favorito: Boolean
)