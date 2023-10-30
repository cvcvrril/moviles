package com.example.recycledinesmr.ui.recycled

import com.example.recycledinesmr.domain.modelo.Pelicula

data class RecyclerState (
    val lista: List<Pelicula>,
    val error: String? = null
)