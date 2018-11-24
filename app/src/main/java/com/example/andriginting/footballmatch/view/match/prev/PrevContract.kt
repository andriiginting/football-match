package com.example.andriginting.footballmatch.view.match.prev

import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.model.league.LeagueModel

interface PrevContract {
    interface View {
        fun initComponent()
        fun showLoadingIndicator()
        fun hidLoadingIndicator()
        fun setSpinnerData(data: String)
        fun showFootBallSchedule(data: ArrayList<PrevMatchModel>)
    }

    interface Presenter {
        fun getMatchDetail(leagueId: Int, list: ArrayList<PrevMatchModel>): ArrayList<PrevMatchModel>
        fun getListOfLeagueTeam(list: ArrayList<LeagueModel>): List<LeagueModel>
    }
}