package com.example.recyclerretrofitinesmr.data.model

import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.example.recyclerretrofitinesmr.utils.Constants
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class PeliculaResponse (
    @SerializedName(Constants.ID)
    val id: Int,
    @SerializedName(Constants.TITULO)
    val titulo: String,
    @SerializedName(Constants.FECHA)
    val fecha: String,
    @SerializedName(Constants.ID_DIRECTOR)
    val idDirector: Int
)

fun PeliculaResponse.toPelicula() : Pelicula {
    val fechaDate =LocalDate.parse(fecha)
    return Pelicula(id, titulo, fechaDate, idDirector)
}
