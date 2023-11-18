package com.example.recyclerretrofitinesmr.data.repository

import com.example.recyclerretrofitinesmr.data.sources.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityRetainedScoped
class DirectorRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getAllDirector(){
        withContext(Dispatchers.IO)
        {remoteDataSource.getAllDirector()}
    }

    suspend fun getDirector(id: String){
        withContext(Dispatchers.IO)
        {remoteDataSource.getDirector(id)}
    }

}