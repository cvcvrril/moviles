package com.example.unapantallainesmr.domain.usecases

import com.example.unapantallainesmr.data.SerieRepository
import javax.inject.Inject

class GetSerieUseCase @Inject constructor(var repository: SerieRepository){
    operator fun invoke(id: Int) = repository.get(id)

}