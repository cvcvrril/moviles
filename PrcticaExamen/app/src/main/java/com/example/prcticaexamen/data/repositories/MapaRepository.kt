package com.example.prcticaexamen.data.repositories

import com.example.prcticaexamen.data.sources.remote.MapaRemoteDataSource
import com.example.prcticaexamen.domain.model.Mapa
import com.example.prcticaexamen.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class MapaRepository @Inject constructor(
    private val remoteDataSource: MapaRemoteDataSource
){

    fun getMapas(): Flow<NetworkResult<List<Mapa>>>{
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.getMapas()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}