package com.example.flowroomsinesmr.ui.main

import com.example.flowroomsinesmr.domain.modelo.Credencial

data class MainState (
    val error: String? = null,
    val credencial: Credencial? = null
)