package com.example.domain.repository

import com.example.domain.models.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}