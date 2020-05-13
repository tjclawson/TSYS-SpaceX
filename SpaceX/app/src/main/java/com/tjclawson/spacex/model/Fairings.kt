package com.tjclawson.spacex.model


import com.google.gson.annotations.SerializedName

data class Fairings(
    val reused: Boolean,
    @SerializedName("recovery_attempt")
    val recoveryAttempt: Boolean,
    val recovered: Boolean,
    val ship: String?
)