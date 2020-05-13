package com.tjclawson.spacex.model


import com.google.gson.annotations.SerializedName
import com.tjclawson.spacex.model.OrbitParams

data class Payload(
    @SerializedName("payload_id")
    val payloadId: String,
    @SerializedName("norad_id")
    val noradId: List<Int>,
    val reused: Boolean,
    val customers: List<String>,
    val nationality: String,
    val manufacturer: String,
    @SerializedName("payload_type")
    val payloadType: String,
    @SerializedName("payload_mass_kg")
    val payloadMassKg: Double,
    @SerializedName("payload_mass_lbs")
    val payloadMassLbs: Double,
    val orbit: String,
    @SerializedName("orbit_params")
    val orbitParams: OrbitParams
)