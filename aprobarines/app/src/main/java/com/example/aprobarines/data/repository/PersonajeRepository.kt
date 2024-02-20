package com.example.aprobarines.data.repository

import com.example.aprobarines.data.sources.remote.PersonajeRemoteDataSource
import com.example.aprobarines.domain.modelo.Personaje
import com.example.aprobarines.domain.modelo.Videojuego
import com.example.aprobarines.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonajeRepository @Inject constructor(
    private val remoteDataSource: PersonajeRemoteDataSource
){

}