package com.example.andriginting.footballmatch.presenter

import com.example.andriginting.footballmatch.model.ClubModel

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