package com.example.aprobarines.ui.screens.registro

import com.example.aprobarines.data.modelo.response.AuthorizacionResponse

data class PantallaRegistroState(
    val error: String? = null,
    val credencial: AuthorizacionResponse? = null
) {
}