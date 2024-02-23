package com.example.aprobarines.data.sources.remote

import com.apollographql.apollo3.ApolloClient
import com.example.aprobarines.domain.modelo.Personaje
import com.example.aprobarines.utils.NetworkResult
import org.example.videojuegos.AddPersonajeMutation
import org.example.videojuegos.DeletePersonajeMutation
import org.example.videojuegos.GetPersonajeQuery
import org.example.videojuegos.GetPersonajesQuery
import javax.inject.Inject

class PersonajeRemoteDataSource @Inject constructor(
    private var apolloClient: ApolloClient
) {

    suspend fun getPersonajes(): NetworkResult<List<Personaje>> {
        try {
            val response = apolloClient.query(GetPersonajesQuery()).execute()
            if (response.hasErrors()) {
                return NetworkResult.Error("Error")
            } else {
                val body = response.data?.getPersonajes?.map {
                    Personaje(it.id, it.nombre, it.descripcion)
                } ?: emptyList()
                if (body.isEmpty()) {
                    return NetworkResult.Error("La lista de personajes está vacía.")
                } else {
                    return NetworkResult.Success(body)
                }
            }
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }

    suspend fun getPersonaje(id: Int): NetworkResult<Personaje>{
        try {
            val response = apolloClient.query(GetPersonajeQuery(id)).execute()
            if (response.hasErrors()) {
                return NetworkResult.Error("Error")
            } else {
                val body = response.data?.getPersonaje?.let {
                    Personaje(it.id, it.nombre, it.descripcion)
                } ?: null
                if (body == null) {
                    return NetworkResult.Error("La lista de personajes está vacía.")
                } else {
                    return NetworkResult.Success(body)
                }
            }
        }catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }

    suspend fun deletePersonaje(id: Int): NetworkResult<Unit>{
        try {
            val response = apolloClient.mutation(DeletePersonajeMutation(id)).execute()
            if (response.hasErrors()){
                return NetworkResult.Error("Error")
            } else {
                return NetworkResult.Success(Unit)
            }
        }catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }

    suspend fun addPersonaje(nombre: String): NetworkResult<Personaje>{
        try {
            val response = apolloClient.mutation(AddPersonajeMutation(nombre)).execute()
            if (response.hasErrors()) {
                return NetworkResult.Error("Error")
            } else {
                val body = response.data?.addPersonaje?.let {
                    Personaje(0,it.nombre, "")
                } ?: null
                if (body == null) {
                    return NetworkResult.Error("La lista de personajes está vacía.")
                } else {
                    return NetworkResult.Success(body)
                }
            }
        }catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }
}