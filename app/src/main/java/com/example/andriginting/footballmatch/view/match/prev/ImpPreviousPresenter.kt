package com.example.andriginting.footballmatch.view.match.prev

import android.annotation.SuppressLint
import android.util.Log
import com.example.andriginting.footballmatch.model.*
import com.example.andriginting.footballmatch.model.league.LeagueModel

import com.example.andriginting.footballmatch.network.NetworkInterface
import com.example.andriginting.footballmatch.network.NetworkModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException

class ImpPreviousPresenter(private val view: PrevContract.View) : PrevContract.Presenter {

    private val request = NetworkModule()
            .getRetrofitClient()?.create(NetworkInterface::class.java)

    @SuppressLint("CheckResult")
    override fun getMatchDetail(leagueId: Int, list: ArrayList<PrevMatchModel>): ArrayList<PrevMatchModel> {
        view.showLoadingIndicator()
        request?.getPreviousMatch(leagueId)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ response ->
                    when {
                        response.isSuccessful -> {
                            val res = response.body()!!.listLeague
                            for (items in res!!.indices) {
                                list.add(PrevMatchModel(
                                        res[items].idEvent.toString(),
                                        res[items].strLeague.toString(),
                                        Team(teamId = res[items].idHomeTeam.toString(), teamName = res[items].strHomeTeam.toString()),
                                        MatchStat(goals = res[items].intHomeScore,
                                                totalShots = res[items].intHomeShots.toString(),
                                                yellowCard = res[items].strHomeYellowCards.toString(),
                                                redCard = res[items].strHomeRedCards.toString()),
                                        Team(teamId = res[items].idAwayTeam.toString(), teamName = res[items].strAwayTeam.toString()),
                                        MatchStat(goals = res[items].intAwayScore,
                                                totalShots = res[items].intAwayShots.toString(),
                                                yellowCard = res[items].strAwayYellowCards.toString(),
                                                redCard = res[items].strAwayRedCards.toString()),
                                        res[items].dateEvent.toString(),
                                        res[items].strTime.toString(),
                                        res[items].strThumb.toString()))
                                view.showFootBallSchedule(list)
                            }
                            view.hidLoadingIndicator()
                        }
                    }
                }, {
                    try {
                        it.cause
                    } catch (e: IOException) {
                        e.stackTrace
                    }
                })
        Log.d("hasilnya", list.toString())
        return list
    }

    @SuppressLint("CheckResult")
    override fun getListOfLeagueTeam(list: ArrayList<LeagueModel>): List<LeagueModel> {
        request?.getAllListOfLeague()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({response ->
                    val result = response.body()?.listLeague
                    when {
                        response.isSuccessful -> {
                            for (items in result!!.indices) {
                                list.add(LeagueModel(result[items].leagueId.toString(),
                                        result[items].leagueName.toString()))
                                view.setSpinnerData(list[items].leagueName)
                            }
                            view.hidLoadingIndicator()
                        }
                    }
                },{ error ->
                    try {
                        error.localizedMessage
                    }catch (e: Exception){
                        e.message
                    }
                })
        return list
    }
}