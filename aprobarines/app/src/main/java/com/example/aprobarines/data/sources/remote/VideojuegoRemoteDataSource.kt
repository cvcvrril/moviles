package com.example.aprobarines.data.sources.remote

import com.apollographql.apollo3.ApolloClient
import com.example.aprobarines.domain.modelo.Videojuego
import com.example.aprobarines.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.example.videojuegos.GetVideojuegosQuery
import javax.inject.Inject

class VideojuegoRemoteDataSource  @Inject constructor(
    private var apolloClient: ApolloClient) {

    suspend fun getVideojuegos() : NetworkResult<List<Videojuego>> {
        try {
            val response = apolloClient.query(GetVideojuegosQuery()).execute()
            if (response.hasErrors()){
                return NetworkResult.Error("Error")
            }else{
                val body = response.data?.getVideojuegos?.map {
                    Videojuego(it.id, it.titulo, it.descripcion)
                } ?: emptyList()
                if (body.isEmpty()){
                    return NetworkResult.Error("La lista de videojuegos está vacía.")
                } else{
                    return NetworkResult.Success(body)
                }
            }
        }catch (e : Exception){
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }
}