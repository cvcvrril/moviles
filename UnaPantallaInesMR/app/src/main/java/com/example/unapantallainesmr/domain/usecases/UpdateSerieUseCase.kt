package com.example.unapantallainesmr.domain.usecases

import com.example.unapantallainesmr.data.SerieRepository
import com.example.unapantallainesmr.domain.modelo.Serie
import javax.inject.Inject

class UpdateSerieUseCase @Inject constructor(var repository: SerieRepository){
    operator fun invoke(serie: Serie) = repository.update(serie)
}