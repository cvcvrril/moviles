package com.example.unapantallainesmr.domain.usecases

import com.example.unapantallainesmr.data.SerieRepository
import com.example.unapantallainesmr.domain.modelo.Serie
import javax.inject.Inject

class DeleteSerieUseCase @Inject constructor(var repository: SerieRepository){
    operator fun invoke(serie: Serie) = repository.delete(serie)

}