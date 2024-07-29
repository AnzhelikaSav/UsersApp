package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class UserNetwork(
    @SerializedName("name")
    val name: UserNameNetwork,
    @SerializedName("email")
    val email: String,
    @SerializedName("picture")
    val picture: PictureNetwork
)