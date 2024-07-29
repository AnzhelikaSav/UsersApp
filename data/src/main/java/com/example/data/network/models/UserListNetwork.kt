package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class UserListNetwork(
    @SerializedName("results")
    val results: List<UserNetwork>
)