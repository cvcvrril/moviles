package com.example.practicaexamenmoviles.domain.model

import java.time.LocalDate

data class Videojuego (
    val id: Int,
    val titulo: String,
    val anyoPublicacion: Int,
    var isSelected: Boolean = false
)