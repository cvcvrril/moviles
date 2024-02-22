package com.example.aprobarines.domain.usecases

import com.example.aprobarines.data.repository.UserRepository
import com.example.aprobarines.domain.modelo.User
import javax.inject.Inject

class DoRegistroUseCase @Inject constructor(
    var repository : UserRepository
) {
    suspend operator fun invoke(user: User) = repository.doRegister(user)

}