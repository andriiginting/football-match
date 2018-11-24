package com.example.andriginting.footballmatch.view.match.next

import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.model.league.LeagueModel

interface NextContract {
    interface View {
        fun initComponent()
        fun showLoadingIndicator()
        fun hidLoadingIndicator()
        fun showFootBallSchedule(data: ArrayList<PrevMatchModel>)
        fun setSpinnerData(data: String)
        fun setupSpiner()
    }

    interface Presenter {
        fun getMatchDetail(leagueId: Int)
        fun getListOfLeague(list: ArrayList<LeagueModel>): List<LeagueModel>
    }
}