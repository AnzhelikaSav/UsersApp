package com.example.domain.usecase

import com.example.domain.models.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class GetUserByUUIDUseCase @Inject constructor(private val repository: UserRepository) {
    fun execute(uuid: UUID): Flow<User> {
        return repository.getUserByUUID(uuid)
    }
}