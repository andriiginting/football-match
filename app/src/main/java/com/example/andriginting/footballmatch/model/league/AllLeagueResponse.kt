package com.example.andriginting.footballmatch.model.league

import com.google.gson.annotations.SerializedName

class AllLeagueResponse {
    @SerializedName("idLeague")
    val leagueId: String? = null

    @SerializedName("strLeague")
    val leagueName: String? = null

    @SerializedName("strSport")
    val leagueSportType: String? = null

    @SerializedName("strLeagueAlternate")
    val leagueSportTypeAlternate: String? = null

}