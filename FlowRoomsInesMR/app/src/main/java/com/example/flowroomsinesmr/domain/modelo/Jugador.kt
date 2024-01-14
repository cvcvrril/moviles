package com.example.flowroomsinesmr.domain.modelo

data class Jugador (
    val id: Int,
    val nick: String,
    val partidas: List<Partida>?

)