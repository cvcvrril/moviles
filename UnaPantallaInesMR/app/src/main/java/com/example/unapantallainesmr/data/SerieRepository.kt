package com.example.unapantallainesmr.data

import com.example.unapantallainesmr.data.modelo.toSerie
import com.example.unapantallainesmr.data.modelo.toSerieEntity
import com.example.unapantallainesmr.domain.modelo.Serie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SerieRepository @Inject constructor(
    val serieDao: SerieDao
) {
    fun getAll() : Flow<List<Serie>> =
        flow{
            emit(serieDao.getAll().map { it.toSerie() })
        }.flowOn(Dispatchers.IO)

    fun get(id: Int) : Flow<Serie?> =
        flow {
            emit(serieDao.get(id)?.toSerie())
        }.flowOn(Dispatchers.IO)

    fun insert(serie: Serie) =
        flow {
            emit(serieDao.insert(serie.toSerieEntity()))
        }.flowOn(Dispatchers.IO)

    fun delete(serie: Serie) =
        flow {
            emit(serieDao.delete(serie.toSerieEntity()))
        }.flowOn(Dispatchers.IO)

    fun update(serie: Serie) =
        flow {
            emit(serieDao.update(serie.toSerieEntity()))
        }.flowOn(Dispatchers.IO)
}