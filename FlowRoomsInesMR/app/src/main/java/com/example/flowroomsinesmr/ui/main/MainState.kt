package com.example.flowroomsinesmr.ui.main

import com.example.flowroomsinesmr.domain.modelo.AuthorizacionResponse
import com.example.flowroomsinesmr.domain.modelo.Credencial

data class MainState (
    val error: String? = null,
    val credencial: AuthorizacionResponse? = null
)