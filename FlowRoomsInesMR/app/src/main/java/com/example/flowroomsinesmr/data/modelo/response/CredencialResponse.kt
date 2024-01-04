package com.example.flowroomsinesmr.data.modelo.response

import com.example.flowroomsinesmr.domain.modelo.Credencial
import com.google.gson.annotations.SerializedName

data class CredencialResponse(
    @SerializedName("username")
    val user: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("email")
    val email : String,
    @SerializedName("autentificado")
    val autentificado: Boolean,
    @SerializedName("rol")
    val rol: String
)


fun CredencialResponse.toCredencial() : Credencial = Credencial(user, password, email, autentificado, rol)