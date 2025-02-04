package com.example.aprobarines.domain.usecases

import com.example.aprobarines.data.repository.UserRepository
import javax.inject.Inject

class DoLoginUseCase @Inject constructor(
    var repository : UserRepository
) {

    suspend operator fun invoke(username: String, password: String) = repository.getLogin(username, password)

}