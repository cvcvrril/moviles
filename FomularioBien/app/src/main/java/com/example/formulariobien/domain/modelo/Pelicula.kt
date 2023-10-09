package com.example.formulariobien.domain.modelo

import java.time.LocalDate

data class Pelicula(
    val titulo: String? = "",
    val director: String? = "",
    val fecha:LocalDate?
)

