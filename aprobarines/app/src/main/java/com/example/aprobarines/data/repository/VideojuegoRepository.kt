package com.example.aprobarines.data.repository

import com.apollographql.apollo3.ApolloClient
import com.example.aprobarines.domain.modelo.Videojuego
import com.example.aprobarines.data.apollo.queries.peliculas.GetVideojuegosQueries


class VideojuegoRepository(
    var apolloClient: ApolloClient
) {

    suspend fun getPeliculas() =
        apolloClient.query(GetVideojuegos()).execute().data?.getPeliculas?.map{
            Videojuego()
        } ?: emptyList()


}