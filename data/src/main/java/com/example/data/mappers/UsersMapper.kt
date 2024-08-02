package com.example.data.mappers

import com.example.data.database.entities.UserEntity
import com.example.data.generate.UserWithUUID
import com.example.data.network.models.UserNetwork
import com.example.domain.models.User
import java.util.UUID

fun UserNetwork.toUserWithUUID(): UserWithUUID {
    return UserWithUUID(
        uuid = UUID.randomUUID(),
        name = name,
        email = email,
        age = dob.age,
        picture = picture
    )
}

fun UserWithUUID.toDomain(): User {
    return User(
        uuid = uuid,
        name = "${name.firstName} ${name.lastName}",
        email = email,
        age = age,
        imageUrl = picture.mediumUrl
    )
}

fun UserWithUUID.toDatabase(): UserEntity {
    return UserEntity(
        uuid = uuid,
        name = "${name.firstName} ${name.lastName}",
        email = email,
        age = age,
        imageUrl = picture.mediumUrl
    )
}

fun UserEntity.toDomain(): User {
    return User(
        uuid = uuid,
        name = name,
        email = email,
        age = age,
        imageUrl = imageUrl ?: ""
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        uuid = uuid,
        name = name,
        email = email,
        age = age,
        imageUrl = imageUrl
    )
}