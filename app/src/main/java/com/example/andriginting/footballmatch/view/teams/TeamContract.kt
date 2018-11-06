package com.example.andriginting.footballmatch.view.teams

import com.example.andriginting.footballmatch.model.league.LeagueModel
import com.example.andriginting.footballmatch.model.teams.TeamModel
import com.example.andriginting.footballmatch.model.teams.TeamResponse
import io.reactivex.Single
import io.reactivex.observers.DisposableObserver
import retrofit2.Response


interface TeamContract {
    interface View{
        fun setupSpinner()
        fun setSpinnerData(data: String)
        fun populateTeamData(data: List<TeamModel>)
        fun setupComponent()
        fun hideLoadingIndicator()
        fun showLoadingIndicator()

    }
    interface Presenter{
        fun getListOfTeam(teamId: Int, list: ArrayList<TeamModel>): ArrayList<TeamModel>
        fun searchTeam(teamName: String, list: ArrayList<TeamModel>): Single<Response<TeamResponse>>?
        fun getListOfLeagueTeam(list: ArrayList<LeagueModel>): List<LeagueModel>
        fun getSearchObserver(): DisposableObserver<Response<TeamResponse>>
    }
}