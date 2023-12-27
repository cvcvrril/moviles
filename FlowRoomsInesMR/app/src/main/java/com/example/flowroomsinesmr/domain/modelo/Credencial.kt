package com.example.flowroomsinesmr.domain.modelo

data class Credencial(
    val user: String,
    val password: String,
    val email : String,
    val autentificado: Boolean
)