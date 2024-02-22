package com.example.aprobarines.data.repository

import com.example.aprobarines.data.modelo.response.AuthorizacionResponse
import com.example.aprobarines.data.sources.remote.UserRemoteDataSource
import com.example.aprobarines.domain.modelo.User
import com.example.aprobarines.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource
){

    fun doRegister(user: User) : Flow<NetworkResult<Unit>>{
        return flow{
            emit(NetworkResult.Loading())
            val result = remoteDataSource.doRegister(user)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun getLogin(username: String, password: String) : Flow<NetworkResult<AuthorizacionResponse>>{
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.getLogin(username, password)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


}