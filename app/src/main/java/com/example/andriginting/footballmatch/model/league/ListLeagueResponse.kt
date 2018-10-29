package com.example.andriginting.footballmatch.model.league

import com.google.gson.annotations.SerializedName

class ListLeagueResponse {
    @SerializedName("leagues")
    val listLeague: ArrayList<AllLeagueResponse>? =null
}