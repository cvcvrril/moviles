package com.example.unapantallainesmr.domain.usecases

import com.example.unapantallainesmr.data.SerieRepository
import javax.inject.Inject

class GetAllSeriesUseCase @Inject constructor(var repository: SerieRepository){
    suspend operator fun invoke() = repository.getAll()
}