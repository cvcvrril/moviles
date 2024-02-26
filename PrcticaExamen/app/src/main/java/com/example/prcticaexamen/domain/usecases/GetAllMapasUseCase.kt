package com.example.prcticaexamen.domain.usecases

import com.example.prcticaexamen.data.repositories.MapaRepository
import javax.inject.Inject

class GetAllMapasUseCase @Inject constructor(
    var repository: MapaRepository
) {

    suspend operator fun invoke() = repository.getMapas()

}