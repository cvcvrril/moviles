package com.example.recycledinesmr.data.modelo

import android.os.Parcelable
import com.example.recycledinesmr.ui.Constantes
import kotlinx.parcelize.Parcelize
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
@Parcelize
data class PeliculaJson (
    @Json(name = Constantes.TITULO)
    val titulo: String = Constantes.VACIO,
    @Json(name = Constantes.DIRECTOR)
    val director: String = Constantes.VACIO,
    @Json(name = Constantes.FECHA)
    val fecha: LocalDate? = null,
    @Json(name = Constantes.ESTRELLAS )
    var estrellas: Float = 0.00f,
    @Json(name = Constantes.CLAS_EDAD)
    val clasificacionEdad: String = Constantes.VACIO,
    @Json(name= Constantes.IMAGEN)
    val imagen: String = Constantes.VACIO
) : Parcelable