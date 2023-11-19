package com.example.recyclerretrofitinesmr.ui.main

import com.example.recyclerretrofitinesmr.domain.Director

data class MainState(
    val directores: List<Director> = emptyList(),
    val selectedDirector: Director? = null,
    val directoresSeleccionados: List<Director> = emptyList(),
    val selectMode: Boolean = false,
    val error: String? = null
)