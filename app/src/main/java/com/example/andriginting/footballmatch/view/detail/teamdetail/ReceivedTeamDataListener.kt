package com.example.andriginting.footballmatch.view.detail.teamdetail

import com.example.andriginting.footballmatch.model.teams.TeamModel

interface ReceivedTeamDataListener {
    fun onDataReceived(data: TeamModel)
}