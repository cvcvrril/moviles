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
    @Json(name = "titulo")
    val titulo: String="",
    @Json(name = "director")
    val director: String="",
    @Json(name = "fecha")
    val fecha: LocalDate? = null,
    @Json(name = "estrellas" )
    var estrellas: Float = 0.00f,
    @Json(name = "clasificacionEdad")
    val clasificacionEdad: String = Constantes.VACIO,
    @Json(name="imagen")
    val imagen: String = ""
) : Parcelable