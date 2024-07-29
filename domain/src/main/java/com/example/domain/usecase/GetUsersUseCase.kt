package com.example.domain.usecase

import com.example.domain.models.User
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UserRepository) {
    suspend fun execute(): List<User> {
        return repository.getUsers()
    }
}