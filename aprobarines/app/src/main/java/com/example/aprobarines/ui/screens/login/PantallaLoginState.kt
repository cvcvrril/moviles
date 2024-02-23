package com.example.aprobarines.ui.screens.login

import com.example.aprobarines.data.modelo.response.AuthorizacionResponse
import com.example.aprobarines.domain.modelo.User

data class PantallaLoginState(
    val error: String? = null,
    val authResponse: AuthorizacionResponse? = null,
    val user: User? = null,
) {
}