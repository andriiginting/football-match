package com.example.andriginting.footballmatch.view.match.next

import android.annotation.SuppressLint
import android.util.Log
import com.example.andriginting.footballmatch.model.MatchStat
import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.model.Team
import com.example.andriginting.footballmatch.network.NetworkInterface
import com.example.andriginting.footballmatch.network.NetworkModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException

class ImpNextPresenter(private val view: NextContract.View,
                       private val list: ArrayList<PrevMatchModel>) : NextContract.Presenter {

    private val request = NetworkModule()
            .getRetrofitClient()?.create(NetworkInterface::class.java)

    @SuppressLint("CheckResult")
    override fun getMatchDetail(leagueId: Int) {
        view.showLoadingIndicator()
        request?.getNextMatch(leagueId)
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
                                        res[items].strThumb.toString()))
                                view.showFootBallSchedule(list)
                                Log.d("looping", res[items].strBanner.toString())
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
    }
}