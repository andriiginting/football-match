package com.example.andriginting.footballmatch.view.match.fav

import com.example.andriginting.footballmatch.db.FootballMatchDB

interface FavoriteMatchContract {
    interface View{
        fun initComponent()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun showNoFavoriteMatch()
        fun hideNoFavoriteMatch()
        fun showListFavMatch(data: List<FootballMatchDB>)
    }

    interface Presenter{
        fun getFavoriteMatch()
    }
}