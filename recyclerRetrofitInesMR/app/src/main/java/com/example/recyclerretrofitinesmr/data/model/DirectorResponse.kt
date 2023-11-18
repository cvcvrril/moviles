package com.example.recyclerretrofitinesmr.data.model

import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class DirectorResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("nacimiento")
    val nacimiento: String,
)


fun DirectorResponse.toDirector() : Director {
    val fechaNacimiento = LocalDate.parse(nacimiento)
    return Director(id, nombre, fechaNacimiento)
}