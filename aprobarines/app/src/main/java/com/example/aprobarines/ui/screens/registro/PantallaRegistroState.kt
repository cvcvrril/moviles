package com.example.aprobarines.ui.screens.registro

import com.example.aprobarines.domain.modelo.User

data class PantallaRegistroState(
    val error: String? = null,
    val user: User? = null,
    val isLoading : Boolean = false,
) {
}