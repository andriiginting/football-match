package com.example.andriginting.footballmatch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LeagueResponse {
    @SerializedName("events")
    @Expose
    var listLeague: List<MatchResponse>? = null
}

class SearchLeagueResponse{
    @SerializedName("event")
    @Expose
    var listEvent: List<MatchResponse>? = null
}