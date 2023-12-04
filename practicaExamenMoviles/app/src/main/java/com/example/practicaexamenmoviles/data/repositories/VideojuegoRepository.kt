package com.example.practicaexamenmoviles.data.repositories

import android.util.Log
import com.example.practicaexamenmoviles.data.sources.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityRetainedScoped
class VideojuegoRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getVideojuegos() =
        withContext(Dispatchers.IO) {
            remoteDataSource.getVideojuegos()
        }



    //TODO: HACER EL GET(ID) Y EL DELETE


}
