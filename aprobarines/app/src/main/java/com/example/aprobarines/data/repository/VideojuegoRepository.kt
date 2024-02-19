package com.example.aprobarines.data.repository

import com.apollographql.apollo3.ApolloClient
import com.example.aprobarines.domain.modelo.Videojuego
import kotlinx.coroutines.flow.Flow
import org.example.videojuegos.GetVideojuegosQuery
import javax.inject.Inject


class VideojuegoRepository @Inject constructor(
    private var apolloClient: ApolloClient
) {
    suspend fun getVideojuegos() : Flow<NetworkResult<Unit>> =
        flow.on

    {
        emit(Net.Loading)
        emit(
        apolloClient.query(GetVideojuegosQuery()).execute().data?.getVideojuegos?.map {
            Videojuego(it.id, it.titulo, it.descripcion)
        } ?: emptyList())

    }
}