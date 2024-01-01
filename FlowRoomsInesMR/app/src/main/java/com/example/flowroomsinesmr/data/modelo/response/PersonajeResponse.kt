package com.example.flowroomsinesmr.data.modelo.response

import com.google.gson.annotations.SerializedName

data class PersonajeResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val nombre: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("idVideojuego")
    val idVideojuego: Int
)