package com.example.flowroomsinesmr.ui.detail

import com.example.flowroomsinesmr.domain.modelo.Videojuego

interface DetailContract {

    sealed class DetailVideojuegoEvent {
        object GetVideojuegos : DetailVideojuegoEvent()
        object ErrorVisto : DetailVideojuegoEvent()
    }

    data class DetailVideojuegoState(
        val error: String? = null,
        val isLoading: Boolean = false,
        val videojuegos: List<Videojuego> = emptyList(),
        )
}