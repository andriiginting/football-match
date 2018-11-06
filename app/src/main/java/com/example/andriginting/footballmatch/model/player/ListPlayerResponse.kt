package com.example.andriginting.footballmatch.model.player

import com.google.gson.annotations.SerializedName

class ListPlayerResponse {
    @SerializedName("player")
    var listPlayer: List<PlayerResponse>? = null
}