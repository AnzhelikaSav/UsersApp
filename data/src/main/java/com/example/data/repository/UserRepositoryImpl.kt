package com.example.data.repository

import com.example.data.database.dao.UserDao
import com.example.data.database.entities.DeleteUserModel
import com.example.data.generate.UserWithUUID
import com.example.data.mappers.toDatabase
import com.example.data.mappers.toDomain
import com.example.data.mappers.toUserWithUUID
import com.example.data.network.api.UsersApi
import com.example.domain.models.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usersApi: UsersApi,
    private val userDao: UserDao,
    private val dispatcher: CoroutineDispatcher
): UserRepository {

    override fun getUsers(): Flow<List<User>> = flow {
        userDao.getUsers().collect { entities ->
            if (entities.isEmpty()) {
                val users = loadUsersFromNetwork()
                userDao.insertUsers(users.map { it.toDatabase() })
            } else {
                emit(entities.map { it.toDomain() })
            }
        }
    }.flowOn(dispatcher)

    override suspend fun getUserByUUID(uuid: UUID): User = withContext(dispatcher) {
        userDao.getUserByUUID(uuid).toDomain()
    }

    override suspend fun deleteUser(uuid: UUID) = withContext(dispatcher) {
        userDao.deleteUser(
            DeleteUserModel(uuid)
        )
    }

    private suspend fun loadUsersFromNetwork(): List<UserWithUUID> {
        return usersApi.getUsers().results.map { it.toUserWithUUID() }
    }
}