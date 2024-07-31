package com.example.data.repository

import com.example.data.database.dao.UserDao
import com.example.data.generate.UserWithUUID
import com.example.data.mappers.toDatabase
import com.example.data.mappers.toDomain
import com.example.data.mappers.toUserWithUUID
import com.example.data.network.api.UsersApi
import com.example.domain.models.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usersApi: UsersApi,
    private val userDao: UserDao,
    private val dispatcher: CoroutineDispatcher
): UserRepository {
    override suspend fun getUsers(): List<User> = withContext(dispatcher) {
        val users = userDao.getUsers()
        if (users.isEmpty()) {
            val loadedUsers = loadUsersFromNetwork()
            userDao.insertUsers(loadedUsers.map { it.toDatabase() })
            loadedUsers.map { it.toDomain() }
        } else {
            users.map { it.toDomain() }
        }
    }

    override suspend fun getUserByUUID(uuid: UUID): User = withContext(dispatcher) {
        userDao.getUserByUUID(uuid).toDomain()
    }

    private suspend fun loadUsersFromNetwork(): List<UserWithUUID> {
        return usersApi.getUsers().results.map { it.toUserWithUUID() }
    }
}