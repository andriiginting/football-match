package com.example.andriginting.footballmatch.view.main

import com.example.andriginting.footballmatch.model.teams.ClubModel

interface MainContract {
    interface View{
        fun initComponent()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
    }

    interface Presenter{
        fun addClub(): List<ClubModel>
    }
}