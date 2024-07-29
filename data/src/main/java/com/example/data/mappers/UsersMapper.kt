package com.example.data.mappers

import com.example.data.database.entities.UserEntity
import com.example.data.network.models.UserListNetwork
import com.example.data.network.models.UserNetwork
import com.example.domain.models.User
import java.util.UUID

fun UserListNetwork.toDomain(): List<User> {
    return this.results.map { it.toDomain() }
}

fun UserNetwork.toDomain(): User {
    return User(
        uuid = UUID.randomUUID(),
        name = "${name.firstName} ${name.lastName}",
        email = email,
        imageUrl = picture.mediumUrl
    )
}

fun UserListNetwork.toDatabase(): List<UserEntity> {
    return this.results.map { it.toDatabase() }
}

fun UserNetwork.toDatabase(): UserEntity {
    return UserEntity(
        uuid = UUID.randomUUID(),
        name = "${name.firstName} ${name.lastName}",
        email = email,
        imageUrl = picture.mediumUrl
    )
}

fun UserEntity.toDomain(): User {
    return User(
        uuid = UUID.randomUUID(),
        name = name,
        email = email,
        imageUrl = imageUrl ?: ""
    )
}