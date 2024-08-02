package com.example.domain.usecase

import com.example.domain.models.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UserRepository) {
    fun execute(searchString: String = ""): Flow<List<User>> {
        return repository.getUsers(searchString)
    }
}