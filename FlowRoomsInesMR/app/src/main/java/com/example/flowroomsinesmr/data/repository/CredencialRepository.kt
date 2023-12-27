package com.example.flowroomsinesmr.data.repository

import com.example.flowroomsinesmr.data.sources.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CredencialRepository @Inject constructor(private val remoteDataSource: RemoteDataSource){

    //TODO: ESTO CAMBIARLO MÁS ADELANTE POR FLOWS (PERO VOY PROBANDO)

    suspend fun getLogin(user: String, password: String) =
        withContext(Dispatchers.IO){
            remoteDataSource.getLogin(user, password)
        }



}