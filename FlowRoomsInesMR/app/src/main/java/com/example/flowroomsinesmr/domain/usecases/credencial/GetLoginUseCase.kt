package com.example.flowroomsinesmr.domain.usecases.credencial

import com.example.flowroomsinesmr.data.repository.CredencialRepository
import com.example.flowroomsinesmr.domain.modelo.AuthorizacionResponse
import com.example.flowroomsinesmr.domain.modelo.Credencial
import com.example.flowroomsinesmr.utils.NetworkResult
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(var repository: CredencialRepository) {

    suspend operator fun invoke(user: String, password: String): NetworkResult<AuthorizacionResponse> = repository.getLogin(user, password)


}