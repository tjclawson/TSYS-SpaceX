package com.tjclawson.spacex.model


import com.tjclawson.spacex.model.Payload

data class SecondStage(
    val block: Int,
    val payloads: List<Payload>
)