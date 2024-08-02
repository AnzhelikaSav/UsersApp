package com.example.data.repository

import com.example.data.database.dao.UserDao
import com.example.data.database.entities.DeleteUserModel
import com.example.data.generate.UserWithUUID
import com.example.data.mappers.toDatabase
import com.example.data.mappers.toDomain
import com.example.data.mappers.toUserEntity
import com.example.data.mappers.toUserWithUUID
import com.example.data.network.api.UsersApi
import com.example.domain.models.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usersApi: UsersApi,
    private val userDao: UserDao,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override fun getUsers(searchString: String): Flow<List<User>> = flow {
        userDao.get().collect { entities ->
            if (entities.isEmpty()) {
                val users = loadUsersFromNetwork()
                userDao.insert(users.map { it.toDatabase() })
            } else {
                emit(entities.map { it.toDomain() })
            }
        }
    }.map { users ->
        users.filter {
            it.name.lowercase()
                .contains(searchString.trim().lowercase())
        }
    }
        .flowOn(dispatcher)

    override fun getUserByUUID(uuid: UUID): Flow<User> =
        userDao.getByUUID(uuid).map { it.toDomain() }

    override suspend fun deleteUser(uuid: UUID) = withContext(dispatcher) {
        userDao.delete(
            DeleteUserModel(uuid)
        )
    }

    override suspend fun update(user: User) = withContext(dispatcher) {
        userDao.update(
            user.toUserEntity()
        )
    }

    private suspend fun loadUsersFromNetwork(): List<UserWithUUID> {
        return usersApi.getUsers().results.map { it.toUserWithUUID() }
    }
}