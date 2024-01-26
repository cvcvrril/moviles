package com.example.unapantallainesmr.ui.main

import com.example.unapantallainesmr.domain.modelo.Serie

data class MainState (
    val editMode: Boolean = false,
    var series: List<Serie> = emptyList(),
    var serie: Serie? = null,
    val error: String? = null,
    val texto: String? = null,
)



