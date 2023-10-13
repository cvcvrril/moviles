package com.example.formulariobien.domain.modelo

import java.time.LocalDate

data class Pelicula(
    val titulo: String? = "",
    val director: String? = "",
    val fecha: LocalDate? = null,
    val cast: String? = "",
    var estrellas: Float = 0.00f,
    val recaudado: Float = 0.00f,       //va en millones
    val generoComedia: Boolean = false,
    val generoTerror: Boolean = false,
    val generoRomance: Boolean = false,
    val generoTragedia: Boolean = false,
    val clasificacionEdad: String = ""

    //Para todos los públicos
    //No recomendado para -7
    //No recomendado para -12
    //No recomendado para -16
    //No recomendado para -18
    )


