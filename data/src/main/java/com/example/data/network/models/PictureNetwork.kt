package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class PictureNetwork(
    @SerializedName("medium")
    val mediumUrl: String,
    @SerializedName("thumbnail")
    val thumbnailUrl: String,
)