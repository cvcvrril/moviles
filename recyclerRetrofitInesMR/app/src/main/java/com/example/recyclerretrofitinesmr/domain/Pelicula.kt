package com.example.recyclerretrofitinesmr.domain

import java.time.LocalDate

data class Pelicula (
    val id: Int,
    val titulo: String,
    val fecha: LocalDate,
    val idDirector: Int
)
