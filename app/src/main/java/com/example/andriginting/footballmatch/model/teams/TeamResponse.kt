package com.example.andriginting.footballmatch.model.teams


import com.google.gson.annotations.SerializedName

class TeamResponse {
    @SerializedName("teams")
    var teams: List<DetailTeamResponse>? = null
}