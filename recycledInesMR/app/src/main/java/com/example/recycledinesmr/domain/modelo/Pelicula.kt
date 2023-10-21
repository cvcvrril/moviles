package com.example.recycledinesmr.domain.modelo

import com.example.recycledinesmr.ui.Constantes
import java.time.LocalDate
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pelicula(
    val titulo: String = Constantes.VACIO,
    val director: String = Constantes.VACIO,
    val fecha: LocalDate? = null,
    var estrellas: Float = 0.00f,
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
    ) : Parcelable


