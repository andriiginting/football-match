package com.example.andriginting.footballmatch.model.teams

import com.google.gson.annotations.SerializedName


class DetailTeamResponse {
    @SerializedName("idTeam")
    var idTeam: String? = null

    @SerializedName("strTeam")
    var teamName: String? = null

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null

    @SerializedName("strDescriptionEN")
    var teamDescription: String? = null

    @SerializedName("strStadium")
    var teamStadium: String? = null

    @SerializedName("intFormedYear")
    var teamFormed: String? = null

}