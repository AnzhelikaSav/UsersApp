package com.example.domain.usecase

import com.example.domain.models.User
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun execute(user: User) {
        repository.update(user)
    }
}