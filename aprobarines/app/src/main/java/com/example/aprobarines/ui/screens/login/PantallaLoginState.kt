package com.example.aprobarines.ui.screens.login

import com.example.aprobarines.data.modelo.response.AuthorizacionResponse

data class PantallaLoginState(
    val error: String? = null,
    val credencial: AuthorizacionResponse? = null
) {
}