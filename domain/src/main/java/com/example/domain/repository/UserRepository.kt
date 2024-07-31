package com.example.domain.repository

import com.example.domain.models.User
import java.util.UUID

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUserByUUID(uuid: UUID): User
}