package com.example.data.generate

import com.example.data.network.models.PictureNetwork
import com.example.data.network.models.UserNameNetwork
import java.util.UUID

data class UserWithUUID(
    val uuid: UUID,
    val name: UserNameNetwork,
    val email: String,
    val age: Int,
    val picture: PictureNetwork
)