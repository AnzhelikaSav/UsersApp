package com.example.data.mappers

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