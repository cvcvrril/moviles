package com.example.recyclerretrofitinesmr.domain

import java.time.LocalDate

data class Director (
    val id: Int,
    var nombre: String,
    val nacimiento: LocalDate,
    val peliculas: List<Pelicula>
)