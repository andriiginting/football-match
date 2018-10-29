package com.example.andriginting.footballmatch.view.match.next

import com.example.andriginting.footballmatch.model.PrevMatchModel

interface NextContract {
    interface View {
        fun initComponent()
        fun showLoadingIndicator()
        fun hidLoadingIndicator()
        fun showFootBallSchedule(data: ArrayList<PrevMatchModel>)
    }

    interface Presenter {
        fun getMatchDetail(leagueId: Int)
    }
}