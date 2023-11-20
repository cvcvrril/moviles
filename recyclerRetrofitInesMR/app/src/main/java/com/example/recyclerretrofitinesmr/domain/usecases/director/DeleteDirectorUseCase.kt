package com.example.recyclerretrofitinesmr.domain.usecases.director

import com.example.recyclerretrofitinesmr.data.repository.DirectorRepository
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityRetainedScoped
class DeleteDirectorUseCase @Inject constructor(
    private val directorRepository: DirectorRepository
) {

    suspend fun deleteDirector(id: String): NetworkResult<Unit> {
        return withContext(Dispatchers.IO)
        { directorRepository.deleteDirector(id) }
    }
}