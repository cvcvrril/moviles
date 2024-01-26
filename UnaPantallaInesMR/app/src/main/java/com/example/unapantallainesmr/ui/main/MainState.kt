package com.example.unapantallainesmr.ui.main

import com.example.unapantallainesmr.domain.modelo.Serie

data class MainState (
    val editMode: Boolean = false,
    val series: List<Serie> = emptyList(),
    val error: String? = null,
    val texto: String? = null,
)



