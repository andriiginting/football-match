package com.example.andriginting.footballmatch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class DetailTeamResponse {
    @SerializedName("idTeam")
    @Expose
    var idTeam: String? = null

    @SerializedName("strTeam")
    @Expose
    var teamName: String? = null

    @SerializedName("strTeamBadge")
    @Expose
    var teamBadge: String? = null
}