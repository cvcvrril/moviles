package com.example.flowroomsinesmr.ui.detail

import com.example.flowroomsinesmr.domain.modelo.Videojuego

interface DetailContract {

    sealed class DetailEvent {
        object GetVideojuegos : DetailEvent()
        object ErrorVisto : DetailEvent()
    }

    data class DetailState(
        val error: String? = null,
        val isLoading: Boolean = false,
        val videojuegos: List<Videojuego> = emptyList(),
        )
}