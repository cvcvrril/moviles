package com.example.recyclerretrofitinesmr.data.model

import com.example.recyclerretrofitinesmr.domain.Director
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class PeliculaResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("titulo")
    val titulo: String,
    @SerializedName("fecha")
    val fecha: String
)

fun PeliculaResponse.toPelicula() : Director {
    val fecha = LocalDate.parse(fecha)
    return Director(id, titulo, fecha)
}