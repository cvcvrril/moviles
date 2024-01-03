package com.example.flowroomsinesmr.data.repository

import com.example.flowroomsinesmr.data.local.VideojuegoDao
import com.example.flowroomsinesmr.data.modelo.entity.toVideojuego
import com.example.flowroomsinesmr.data.modelo.entity.toVideojuegoEntity
import com.example.flowroomsinesmr.data.sources.remote.RemoteDataSource
import com.example.flowroomsinesmr.domain.modelo.Videojuego
import com.example.flowroomsinesmr.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VideojuegoRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val videojuegoDao: VideojuegoDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun getAllVideojuegos(): Flow<NetworkResult<List<Videojuego>>> {
        return flow {
            emit(getAllVideojuegosCacheo())
            emit(NetworkResult.Loading())
            val result = remoteDataSource.getAllVideojuegosFlow()
            emit(result)
            //Cache to database if response is successful
            if (result is NetworkResult.Success) {
                result.data?.let { it ->
                    videojuegoDao.deleteAll(it.map { it.toVideojuegoEntity() })
                    videojuegoDao.insertAll(it.map { it.toVideojuegoEntity() })
                }
            }
        }.flowOn(dispatcher)
    }

    suspend fun getAllVideojuegosCacheo(): NetworkResult<List<Videojuego>> {
        return withContext(dispatcher) {
            return@withContext videojuegoDao.getAll().let { list ->
                NetworkResult.Success(list.map { it.toVideojuego() })
            }
        }
    }



}