package com.example.domain.usecase

import com.example.domain.models.User
import com.example.domain.repository.UserRepository
import java.util.UUID
import javax.inject.Inject

class GetUserByUUIDUseCase @Inject constructor(private val repository: UserRepository) {
    suspend fun execute(uuid: UUID): User {
        return repository.getUserByUUID(uuid)
    }
}