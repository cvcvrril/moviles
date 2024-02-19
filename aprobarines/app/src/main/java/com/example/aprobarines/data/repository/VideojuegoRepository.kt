package com.example.aprobarines.data.repository

import com.apollographql.apollo3.ApolloClient
import com.example.aprobarines.data.sources.remote.VideojuegoRemoteDataSource
import com.example.aprobarines.domain.modelo.Videojuego
import com.example.aprobarines.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import org.example.videojuegos.GetVideojuegosQuery
import javax.inject.Inject


class VideojuegoRepository @Inject constructor(
    private val remoteDataSource: VideojuegoRemoteDataSource
) {
    fun getVideojuegos() : Flow<NetworkResult<List<Videojuego>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.getVideojuegos()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}