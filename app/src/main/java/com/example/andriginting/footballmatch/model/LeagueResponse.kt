package com.example.andriginting.footballmatch.model

import com.google.gson.annotations.SerializedName

class LeagueResponse {
    @SerializedName("events")
    var listLeague: List<MatchResponse>? = null
}