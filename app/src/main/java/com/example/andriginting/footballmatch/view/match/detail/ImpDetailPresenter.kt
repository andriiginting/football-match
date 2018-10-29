package com.example.andriginting.footballmatch.view.match.detail

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.example.andriginting.footballmatch.db.FootballDB
import com.example.andriginting.footballmatch.db.FootballDB.Companion.AWAY_RED_CARD
import com.example.andriginting.footballmatch.db.FootballDB.Companion.AWAY_SCORE
import com.example.andriginting.footballmatch.db.FootballDB.Companion.AWAY_TEAM_ID
import com.example.andriginting.footballmatch.db.FootballDB.Companion.AWAY_TEAM_NAME
import com.example.andriginting.footballmatch.db.FootballDB.Companion.AWAY_TOTAL_SHOT
import com.example.andriginting.footballmatch.db.FootballDB.Companion.AWAY_YELLOW_CARD
import com.example.andriginting.footballmatch.db.FootballDB.Companion.HOME_RED_CARD
import com.example.andriginting.footballmatch.db.FootballDB.Companion.HOME_SCORE
import com.example.andriginting.footballmatch.db.FootballDB.Companion.HOME_TEAM_ID
import com.example.andriginting.footballmatch.db.FootballDB.Companion.HOME_TEAM_NAME
import com.example.andriginting.footballmatch.db.FootballDB.Companion.HOME_TOTAL_SHOT
import com.example.andriginting.footballmatch.db.FootballDB.Companion.HOME_YELLOW_CARD
import com.example.andriginting.footballmatch.db.FootballDB.Companion.MATCH_BANNER
import com.example.andriginting.footballmatch.db.FootballDB.Companion.MATCH_DATE
import com.example.andriginting.footballmatch.db.FootballDB.Companion.MATCH_ID
import com.example.andriginting.footballmatch.db.FootballDB.Companion.MATCH_LEAGUE
import com.example.andriginting.footballmatch.db.FootballDB.Companion.TABLE_MATCH_NAME
import com.example.andriginting.footballmatch.extension.databaseHelper
import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.network.NetworkInterface
import com.example.andriginting.footballmatch.network.NetworkModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.io.IOException

class ImpDetailPresenter(private val view: DetailMatchContract.View,
                         private val context: Context) : DetailMatchContract.Presenter {


    private val request = NetworkModule()
            .getRetrofitClient()?.create(NetworkInterface::class.java)

    @SuppressLint("CheckResult")
    override fun getHomeBadge(homeTeamId: String) {
        request?.getDetailTeam(homeTeamId)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    val team = it.body()?.teams?.get(0)
                    when {
                        it.isSuccessful -> {
                            team?.teamBadge?.let { it1 -> view.setHomeClubLogo(it1) }
                            Log.d("badge-club", team?.teamBadge.toString())
                        }
                    }
                }, { error ->
                    try {
                        error.cause
                    } catch (e: IOException) {
                        e.stackTrace
                    }
                })
    }

    @SuppressLint("CheckResult")
    override fun getAwayBadge(awayTeamId: String) {
        request?.getDetailTeam(awayTeamId)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    val team = it.body()?.teams?.get(0)
                    when {
                        it.isSuccessful -> {
                            team?.teamBadge?.let { it1 -> view.setAwayClubLogo(it1) }
                            Log.d("badge-club", team?.teamBadge.toString())
                        }
                    }
                }, { error ->
                    try {
                        error.cause
                    } catch (e: IOException) {
                        e.stackTrace
                    }
                })
    }

    override fun saveMatchToDB(data: PrevMatchModel) {
        try {
            context.databaseHelper.use {
                insert(TABLE_MATCH_NAME,
                        MATCH_ID to data.matchId,
                        MATCH_LEAGUE to data.league,
                        HOME_TEAM_ID to data.homeTeam.teamId,
                        HOME_TEAM_NAME to data.homeTeam.teamName,
                        HOME_SCORE to data.homeStat.goals,
                        HOME_TOTAL_SHOT to data.homeStat.totalShots,
                        HOME_YELLOW_CARD to data.homeStat.yellowCard,
                        HOME_RED_CARD to data.homeStat.redCard,
                        AWAY_TEAM_ID to data.awayTeam.teamId,
                        AWAY_TEAM_NAME to data.awayTeam.teamName,
                        AWAY_SCORE to data.awayStat.goals,
                        AWAY_TOTAL_SHOT to data.awayStat.totalShots,
                        AWAY_YELLOW_CARD to data.awayStat.yellowCard,
                        AWAY_RED_CARD to data.awayStat.redCard,
                        MATCH_DATE to data.dateMatch,
                        MATCH_BANNER to data.banner)
            }
            view.showSnackbar("Match Saved")
        } catch (err: SQLiteConstraintException) {
            view.showSnackbar("Something Wrong!")
        }
    }

    override fun removeMatchFromDB(data: PrevMatchModel) {
        try {
            context.databaseHelper.use {
                delete(TABLE_MATCH_NAME, "MATCH_ID = {id}", "id" to data.matchId)
            }
            view.showSnackbar("Remove Match From Favorite")
        } catch (err: SQLiteConstraintException) {
            view.showSnackbar("Something Wrong!")
        }
    }

    override fun favMatchState(data: PrevMatchModel): Boolean {
        var fav = false
        context.databaseHelper.use {
            val res = select(TABLE_MATCH_NAME).whereArgs("(MATCH_ID = {id})", "id" to data.matchId)
            val favMatch = res.parseList(classParser<FootballDB>())
            fav = favMatch.isNotEmpty()
        }
        return fav
    }
}