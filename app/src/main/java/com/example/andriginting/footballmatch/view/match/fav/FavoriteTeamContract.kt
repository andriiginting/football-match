package com.example.andriginting.footballmatch.view.match.fav

import com.example.andriginting.footballmatch.db.FootballTeamDB

interface FavoriteTeamContract {
    interface View{
        fun initComponent()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun showNoFavoriteTeam()
        fun hideNoFavoriteTeam()
        fun showListFavTeam(data: List<FootballTeamDB>)
    }

    interface Presenter{
        fun getFavoriteTeam()
    }
}