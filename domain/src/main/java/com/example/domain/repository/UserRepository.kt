package com.example.domain.repository

import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface UserRepository {
    fun getUsers(): Flow<List<User>>
    fun getUserByUUID(uuid: UUID): Flow<User>
    suspend fun deleteUser(uuid: UUID)
    suspend fun update(user: User)
}