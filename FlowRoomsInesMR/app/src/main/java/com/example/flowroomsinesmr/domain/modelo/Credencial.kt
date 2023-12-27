package com.example.flowroomsinesmr.domain.modelo

import com.example.flowroomsinesmr.data.modelo.response.CredencialResponse

data class Credencial(
    val user: String,
    val password: String,
    val email : String,
    val autentificado: Boolean
)

fun Credencial.toCredencialResponse(): CredencialResponse = CredencialResponse(user, password, email, autentificado)