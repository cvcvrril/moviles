package com.example.unapantallainesmr.ui.main

import com.example.unapantallainesmr.domain.modelo.Serie

data class MainState (
    var editMode: Boolean = false,
    var series: List<Serie> = emptyList(),
    var serie: Serie? = null,
    val error: String? = null,
    val texto: String? = null,
    val id: Int = 1
)



