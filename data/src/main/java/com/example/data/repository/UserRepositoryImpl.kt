package com.example.data.repository

import com.example.data.mappers.toDomain
import com.example.data.network.api.UsersApi
import com.example.domain.models.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usersApi: UsersApi,
    private val dispatcher: CoroutineDispatcher
): UserRepository {
    override suspend fun getUsers(): List<User> = withContext(dispatcher) {
        usersApi.getUsers().toDomain()
    }

}