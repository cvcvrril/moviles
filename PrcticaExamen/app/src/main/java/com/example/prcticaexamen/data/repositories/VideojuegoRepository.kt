package com.example.prcticaexamen.data.repositories

import com.example.prcticaexamen.data.sources.local.LocalDataSource
import com.example.prcticaexamen.domain.model.Videojuego
import com.example.prcticaexamen.utils.ServiceResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityRetainedScoped
class VideojuegoRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {

    fun getAllVideojuegosRoom() : Flow<ServiceResult<List<Videojuego>>>{
        return flow {
            emit(ServiceResult.Loading())
            val result = localDataSource.getAllVideojuegosRoom()
            emit(result)
        }
    }



}