package com.example.andriginting.footballmatch.view.match.fav

import com.example.andriginting.footballmatch.db.FootballDB

interface FavoriteContract {
    interface View{
        fun initComponent()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun showNoFavoriteMatch()
        fun hideNoFavoriteMatch()
        fun showListFavMatch(data: List<FootballDB>)
    }

    interface Presenter{
        fun getFavoriteMatch()
    }
}