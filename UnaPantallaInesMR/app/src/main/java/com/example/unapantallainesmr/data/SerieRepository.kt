package com.example.unapantallainesmr.data

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
            emit((1..100).map { i -> Serie(i,"titulo $i") })
        }.flowOn(Dispatchers.IO)
}