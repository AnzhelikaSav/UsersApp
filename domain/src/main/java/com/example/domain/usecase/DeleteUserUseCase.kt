package com.example.domain.usecase

import com.example.domain.repository.UserRepository
import java.util.UUID
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend fun execute(uuid: UUID) {
        repository.deleteUser(uuid)
    }
}