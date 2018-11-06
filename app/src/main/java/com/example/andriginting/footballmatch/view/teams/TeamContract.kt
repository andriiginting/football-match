package com.example.andriginting.footballmatch.view.teams

import com.example.andriginting.footballmatch.model.league.LeagueModel
import com.example.andriginting.footballmatch.model.teams.TeamModel


interface TeamContract {
    interface View{
        fun setupSpinner()
        fun setSpinnerData(data: String)
        fun populateTeamData(data: TeamModel)
        fun setupComponent()
        fun hideLoadingIndicator()
        fun showLoadingIndicator()

    }
    interface Presenter{
        fun getListOfTeam(teamId: Int, list: ArrayList<TeamModel>): ArrayList<TeamModel>
        fun getListOfLeagueTeam(list: ArrayList<LeagueModel>): List<LeagueModel>
    }
}