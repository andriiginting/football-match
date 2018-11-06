package com.example.andriginting.footballmatch.view.teams

import android.annotation.SuppressLint
import com.example.andriginting.footballmatch.model.league.LeagueModel
import com.example.andriginting.footballmatch.model.teams.TeamModel
import com.example.andriginting.footballmatch.network.NetworkInterface
import com.example.andriginting.footballmatch.network.NetworkModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImpTeamPresenter(val view: TeamContract.View) : TeamContract.Presenter {

    private var request = NetworkModule().getRetrofitClient()?.create(NetworkInterface::class.java)

    @SuppressLint("CheckResult")
    override fun getListOfTeam(teamId: Int,
                               list: ArrayList<TeamModel>): ArrayList<TeamModel> {
        request?.getDetailTeam(teamId)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ response ->
                    when {
                        response.isSuccessful -> {
                            for (items in response.body()?.teams!!.indices) {
                                list.add(TeamModel(
                                        response.body()?.teams?.get(items)?.idTeam.toString(),
                                        response.body()?.teams?.get(items)?.teamName.toString(),
                                        response.body()?.teams?.get(items)?.teamFormed.toString(),
                                        response.body()?.teams?.get(items)?.teamBadge.toString(),
                                        response.body()?.teams?.get(items)?.teamStadium.toString(),
                                        response.body()?.teams?.get(items)?.teamDescription.toString()
                                ))
                                view.populateTeamData(list[items])
                            }
                            view.hideLoadingIndicator()
                        }
                    }
                }, { error ->
                    error.localizedMessage
                })
        return list
    }

    @SuppressLint("CheckResult")
    override fun getListOfLeagueTeam(list: ArrayList<LeagueModel>): List<LeagueModel> {
        request?.getAllListOfLeague()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ response ->
                    val result = response.body()?.listLeague
                    when {
                        response.isSuccessful -> {
                            for (items in result!!.indices) {
                                list.add(LeagueModel(result[items].leagueId.toString(),
                                        result[items].leagueName.toString()))
                                view.setSpinnerData(list[items].leagueName)
                            }
                            view.hideLoadingIndicator()
                        }
                    }

                }, { error ->
                    error.localizedMessage
                })
        return list
    }
}