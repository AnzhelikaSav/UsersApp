package com.example.domain.models

import java.util.UUID

data class User(
    val uuid: UUID,
    val name: String,
    val email: String,
    val age: Int,
    val imageUrl: String
)