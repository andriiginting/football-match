package com.example.andriginting.footballmatch.model.teams

import com.example.andriginting.footballmatch.model.teams.DetailTeamResponse
import com.google.gson.annotations.SerializedName

class TeamResponse {
    @SerializedName("teams")
    var teams: List<DetailTeamResponse>? = null
}