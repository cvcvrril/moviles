package com.example.recyclerretrofitinesmr.domain.usecases

import com.example.recyclerretrofitinesmr.data.repository.DirectorRepository
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityRetainedScoped
class GetDirectorUseCase @Inject constructor(
    private val directorRepository: DirectorRepository
) {

    suspend fun getDirector(id: String): NetworkResult<Director> {
        return withContext(Dispatchers.IO)
        { directorRepository.getDirector(id) }
    }

}