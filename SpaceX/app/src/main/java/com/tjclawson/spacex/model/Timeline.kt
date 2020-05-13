package com.tjclawson.spacex.model


import com.google.gson.annotations.SerializedName

data class Timeline(
    @SerializedName("webcast_liftoff")
    val webcastLiftoff: Int,
    @SerializedName("go_for_prop_loading")
    val goForPropLoading: Int,
    @SerializedName("rp1_loading")
    val rp1Loading: Int,
    @SerializedName("stage1_lox_loading")
    val stage1LoxLoading: Int,
    @SerializedName("stage2_lox_loading")
    val stage2LoxLoading: Int,
    @SerializedName("engine_chill")
    val engineChill: Int,
    @SerializedName("prelaunch_checks")
    val prelaunchChecks: Int,
    @SerializedName("propellant_pressurization")
    val propellantPressurization: Int,
    @SerializedName("go_for_launch")
    val goForLaunch: Int,
    val ignition: Int,
    val liftoff: Int,
    val maxq: Int,
    val meco: Int,
    @SerializedName("stage_sep")
    val stageSep: Int,
    @SerializedName("second_stage_ignition")
    val secondStageIgnition: Int,
    @SerializedName("fairing_deploy")
    val fairingDeploy: Int,
    @SerializedName("first_stage_boostback_burn")
    val firstStageBoostbackBurn: Int,
    @SerializedName("first_stage_entry_burn")
    val firstStageEntryBurn: Int,
    @SerializedName("first_stage_landing")
    val firstStageLanding: Int,
    @SerializedName("seco-1")
    val seco1: Int,
    @SerializedName("second_stage_restart")
    val secondStageRestart: Int,
    @SerializedName("seco-2")
    val seco2: Int,
    @SerializedName("payload_deploy")
    val payloadDeploy: Int
)