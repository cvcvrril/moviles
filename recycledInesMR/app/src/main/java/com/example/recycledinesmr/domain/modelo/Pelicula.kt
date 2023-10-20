package com.example.recycledinesmr.domain.modelo

import com.example.recycledinesmr.ui.Constantes
import java.time.LocalDate

data class Pelicula(
    val titulo: String? = Constantes.VACIO,
    val director: String? = Constantes.VACIO,
    val fecha: LocalDate? = null,
    val cast: String? = Constantes.VACIO,
    var estrellas: Float = 0.00f,
    val recaudado: Float = 0.00f,       //va en millones
    val generoComedia: Boolean = false,
    val generoTerror: Boolean = false,
    val generoRomance: Boolean = false,
    val generoTragedia: Boolean = false,
    val clasificacionEdad: String = Constantes.VACIO

    //Para todos los públicos
    //No recomendado para -7
    //No recomendado para -12
    //No recomendado para -16
    //No recomendado para -18
    )


