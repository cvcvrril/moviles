package com.example.recyclerretrofitinesmr.data.repository

import android.util.Log
import com.example.recyclerretrofitinesmr.data.sources.remote.RemoteDataSource
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityRetainedScoped
class DirectorRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getAllDirector() : NetworkResult<List<Director>>{
       return withContext(Dispatchers.IO)
        {remoteDataSource.getAllDirector()}
        Log.d("Directores (DirectorRepository)", "Directores: ${remoteDataSource.getAllDirector()}")
    }

    suspend fun getDirector(id: String) : NetworkResult<Director>{
        return withContext(Dispatchers.IO)
        {remoteDataSource.getDirector(id)}
    }

}