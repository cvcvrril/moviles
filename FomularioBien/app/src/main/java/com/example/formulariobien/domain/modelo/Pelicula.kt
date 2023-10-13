package com.example.formulariobien.domain.modelo

import java.time.LocalDate

data class Pelicula(
    val titulo: String? = "",
    val director: String? = "",
    val fecha: LocalDate?,
    val cast: String? = "",
    var estrellas: Float = 0.00f,
    val presupuesto: Int = 0,
    val generoComedia: Boolean = false,
    val generoTerror: Boolean = false,
    val generoRomance: Boolean = false,
    val generoTragedia: Boolean = false,
    )


