package com.example.flowroomsinesmr.data.modelo

import com.example.flowroomsinesmr.data.modelo.entity.JugadorEntity
import com.example.flowroomsinesmr.domain.modelo.Jugador

fun JugadorEntity.toJugador() : Jugador{
    return Jugador(this.id, this.nick, null)
}

fun Jugador.toJugadorEntity() : JugadorEntity{
    return JugadorEntity(this.id, this.nick)
}
