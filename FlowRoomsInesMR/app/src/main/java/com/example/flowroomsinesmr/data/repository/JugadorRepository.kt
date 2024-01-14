package com.example.flowroomsinesmr.data.repository

import com.example.flowroomsinesmr.data.dao.JugadorDao
import com.example.flowroomsinesmr.data.modelo.toJugador
import javax.inject.Inject

class JugadorRepository @Inject constructor(private val jugadorDao: JugadorDao){

    suspend fun getAllJugadores() =
        jugadorDao.getAll().map { it.toJugador() }





}