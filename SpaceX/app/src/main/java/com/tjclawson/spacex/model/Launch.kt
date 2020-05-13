package com.tjclawson.spacex.model


import com.google.gson.annotations.SerializedName

data class Launch(
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("mission_name")
    val missionName: String,
    @SerializedName("mission_id")
    val missionId: List<String>,
    @SerializedName("launch_year")
    val launchYear: String,
    @SerializedName("launch_date_unix")
    val launchDateUnix: Int,
    @SerializedName("launch_date_utc")
    val launchDateUtc: String,
    @SerializedName("launch_date_local")
    val launchDateLocal: String,
    @SerializedName("is_tentative")
    val isTentative: Boolean,
    @SerializedName("tentative_max_precision")
    val tentativeMaxPrecision: String,
    val tbd: Boolean,
    @SerializedName("launch_window")
    val launchWindow: Int,
    val rocket: Rocket,
    val ships: List<String>,
    val telemetry: Telemetry,
    @SerializedName("launch_site")
    val launchSite: LaunchSite,
    @SerializedName("launch_success")
    val launchSuccess: Boolean,
    val links: Links,
    val details: String,
    val upcoming: Boolean,
    @SerializedName("static_fire_date_utc")
    val staticFireDateUtc: String,
    @SerializedName("static_fire_date_unix")
    val staticFireDateUnix: Int,
    val timeline: Timeline,
    val crew: Any?
)