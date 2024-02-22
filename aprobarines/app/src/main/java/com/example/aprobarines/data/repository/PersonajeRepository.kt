package com.example.aprobarines.data.repository

import com.example.aprobarines.data.sources.remote.PersonajeRemoteDataSource
import com.example.aprobarines.domain.modelo.Personaje
import com.example.aprobarines.domain.modelo.Videojuego
import com.example.aprobarines.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PersonajeRepository @Inject constructor(
    private val remoteDataSource: PersonajeRemoteDataSource
){

    fun getPersonajes() : Flow<NetworkResult<List<Personaje>>>{
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.getPersonajes()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun getPersonaje(id : Int) : Flow<NetworkResult<Personaje>>{
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.getPersonaje(id)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}