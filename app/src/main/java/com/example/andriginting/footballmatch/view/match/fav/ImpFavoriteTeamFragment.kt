package com.example.andriginting.footballmatch.view.match.fav

import android.content.Context
import com.example.andriginting.footballmatch.db.FootballTeamDB
import com.example.andriginting.footballmatch.extension.databaseHelper
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ImpFavoriteTeamFragment(val view: FavoriteTeamContract.View,
                              val context: Context): FavoriteTeamContract.Presenter {

    override fun getFavoriteTeam() {
        view.showLoadingIndicator()
        context.databaseHelper.use {
            view.hideLoadingIndicator()
            val res = select(FootballTeamDB.TABLE_TEAM_NAME)
            val favDb = res.parseList(classParser<FootballTeamDB>())
            if (favDb.isEmpty()) {
                view.showNoFavoriteTeam()
            } else {
                view.hideNoFavoriteTeam()
                view.showListFavTeam(favDb)
            }
        }
    }
}