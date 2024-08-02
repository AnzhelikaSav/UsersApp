package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class DayOfBirthNetwork(
    @SerializedName("age")
    val age: Int
)