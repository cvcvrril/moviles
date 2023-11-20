package com.example.recyclerretrofitinesmr.data.model

import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.example.recyclerretrofitinesmr.utils.Constants
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class DirectorResponse(
    @SerializedName(Constants.ID)
    val id: Int,
    @SerializedName(Constants.NOMBRE)
    val nombre: String,
    @SerializedName(Constants.NACIMIENTO)
    val nacimiento: String,
)


fun DirectorResponse.toDirector() : Director {
    val fechaNacimiento = LocalDate.parse(nacimiento)
    return Director(id, nombre, fechaNacimiento)
}