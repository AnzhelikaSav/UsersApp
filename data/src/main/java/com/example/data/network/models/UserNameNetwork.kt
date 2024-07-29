package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class UserNameNetwork(
    @SerializedName("first")
    val firstName: String,
    @SerializedName("last")
    val lastName: String
)