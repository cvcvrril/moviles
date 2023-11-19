package com.example.recyclerretrofitinesmr.domain.usecases

import android.util.Log
import com.example.recyclerretrofitinesmr.data.repository.DirectorRepository
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityRetainedScoped
class GetAllDirectorUseCase @Inject constructor(
    private val directorRepository: DirectorRepository
) {

    suspend fun getAllDirector(): NetworkResult<List<Director>> {
        return withContext(Dispatchers.IO)
        { directorRepository.getAllDirector() }
        Log.d("Directores (DirectorRepository)", "Directores: ${directorRepository.getAllDirector()}")
    }

}