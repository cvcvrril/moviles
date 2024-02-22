package com.example.aprobarines.domain.modelo

import com.example.aprobarines.data.modelo.response.UserResponse

data class User(
    val username: String = "",
    val password: String = "",
    val rol: String = "",
) {
    fun toUserResponse(): UserResponse = UserResponse(username, password)
}