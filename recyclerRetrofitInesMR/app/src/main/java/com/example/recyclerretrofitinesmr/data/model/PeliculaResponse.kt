package com.example.recyclerretrofitinesmr.data.model

import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class PeliculaResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("titulo")
    val titulo: String,
    @SerializedName("fecha")
    val fecha: String,
    @SerializedName("idDirector")
    val idDirector: Int
)

fun PeliculaResponse.toPelicula() : Pelicula {
    val fechaDate =LocalDate.parse(fecha)
    return Pelicula(id, titulo, fechaDate, idDirector)
}
