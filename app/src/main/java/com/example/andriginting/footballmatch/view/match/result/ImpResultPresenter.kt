package com.example.andriginting.footballmatch.view.match.result

import android.annotation.SuppressLint
import android.util.Log
import com.example.andriginting.footballmatch.model.*
import com.example.andriginting.footballmatch.network.NetworkInterface
import com.example.andriginting.footballmatch.network.NetworkModule
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ImpResultPresenter(val view: ResultContract.View) : ResultContract.Presenter {

    private val request = NetworkModule()
            .getRetrofitClient()?.create(NetworkInterface::class.java)

    @SuppressLint("CheckResult")
    override fun getMatchByName(teamName: String): Single<Response<SearchLeagueResponse>>? {
        return request?.searchEventsByEventsName(teamName)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

    override fun getMatchObserver(): DisposableObserver<Response<SearchLeagueResponse>> {
        return object : DisposableObserver<Response<SearchLeagueResponse>>() {
            override fun onComplete() {
                Log.d("data-search", "called on complete")
            }

            override fun onNext(t: Response<SearchLeagueResponse>) {
                view.showLoadingIndicator()
                val list: ArrayList<PrevMatchModel> = ArrayList()
                when {
                    t.code() == 200 -> {
                        val response = t.body()?.listEvent
                        Log.d("data-search", "sukses fetch ${response?.size}")
                        when {
                            t.isSuccessful -> {
                                val res = t.body()!!.listEvent
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
                                    view.populateData(list)
                                }
                            }
                        }
                        view.hideLoadingIndicator()

                    }
                    t.code() == 500 -> {
                        Log.d("data-search", "server error")
                    }
                    else -> {
                        Log.d("data-search", "failed")
                    }
                }
            }

            override fun onError(e: Throwable) {
                Log.e("error-searcch", e.message)
                view.hideLoadingIndicator()
            }

        }
    }
}