package com.example.andriginting.footballmatch.model.player

import com.google.gson.annotations.SerializedName

class PlayerResponse {

    @SerializedName("idPlayer")
    var playerId: String? = null

    @SerializedName("idTeam")
    var teamId: String? = null

    @SerializedName("strPlayer")
    var playerName: String? = null

    @SerializedName("strPosition")
    var playerPosition: String? = null

    @SerializedName("strCutout")
    var playerImage: String? = null

    @SerializedName("strWeight")
    var playerWeight: String? = null

    @SerializedName("strHeight")
    var playerHeight: String? = null

    @SerializedName("strDescriptionEN")
    var playerDetail: String? = null

    @SerializedName("strFanart2")
    var playerBanner: String? = null
}