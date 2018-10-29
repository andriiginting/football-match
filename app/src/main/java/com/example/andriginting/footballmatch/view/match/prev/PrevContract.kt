package com.example.andriginting.footballmatch.view.match.prev

import com.example.andriginting.footballmatch.model.PrevMatchModel

interface PrevContract {
    interface View {
        fun initComponent()
        fun showLoadingIndicator()
        fun hidLoadingIndicator()
        fun showFootBallSchedule(data: ArrayList<PrevMatchModel>)
    }

    interface Presenter {
        fun getMatchDetail(leagueId: Int): ArrayList<PrevMatchModel>
    }
}