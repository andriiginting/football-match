package com.example.andriginting.footballmatch.view.match.fav

import android.content.Context
import com.example.andriginting.footballmatch.db.FootballMatchDB
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.TABLE_MATCH_NAME
import com.example.andriginting.footballmatch.extension.databaseHelper
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ImpFavoritePresenter(private val view: FavoriteMatchContract.View,
                           private val context: Context) : FavoriteMatchContract.Presenter {

    override fun getFavoriteMatch() {
        view.showLoadingIndicator()
        context.databaseHelper.use {
            view.hideLoadingIndicator()
            val res = select(TABLE_MATCH_NAME)
            val favDb = res.parseList(classParser<FootballMatchDB>())
            if (favDb.isEmpty()) {
                view.showNoFavoriteMatch()
            } else {
                view.hideNoFavoriteMatch()
                view.showListFavMatch(favDb)
            }
        }
    }
}