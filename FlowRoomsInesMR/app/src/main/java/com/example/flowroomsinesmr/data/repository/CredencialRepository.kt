package com.example.flowroomsinesmr.data.repository

import com.example.flowroomsinesmr.data.sources.remote.RemoteDataSource
import com.example.flowroomsinesmr.domain.modelo.AuthorizacionResponse
import com.example.flowroomsinesmr.domain.modelo.Credencial
import com.example.flowroomsinesmr.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CredencialRepository @Inject constructor(private val remoteDataSource: RemoteDataSource){

    suspend fun getLogin(user: String, password: String)  =
        withContext(Dispatchers.IO){
            remoteDataSource.getLogin(user, password)
        }

    suspend fun doRegister(credencial: Credencial)=
        withContext(Dispatchers.IO){
            remoteDataSource.doRegister(credencial)
        }

    suspend fun forgotPassword(credencial: Credencial)=
        withContext(Dispatchers.IO){
            remoteDataSource.forgotPassword(credencial)
        }


}