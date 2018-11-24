package com.example.andriginting.footballmatch.view.teams

import android.annotation.SuppressLint
import android.util.Log
import com.example.andriginting.footballmatch.model.league.LeagueModel
import com.example.andriginting.footballmatch.model.teams.TeamModel
import com.example.andriginting.footballmatch.model.teams.TeamResponse
import com.example.andriginting.footballmatch.network.NetworkInterface
import com.example.andriginting.footballmatch.network.NetworkModule
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ImpTeamPresenter(val view: TeamContract.View) : TeamContract.Presenter {


    private var request = NetworkModule().getRetrofitClient()?.create(NetworkInterface::class.java)

    @SuppressLint("CheckResult")
    override fun getListOfTeam(teamId: Int,
                               list: ArrayList<TeamModel>): ArrayList<TeamModel> {
        view.showLoadingIndicator()
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
                            }
                            view.populateTeamData(list)
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

    @SuppressLint("CheckResult")
    override fun searchTeam(teamName: String): Single<Response<TeamResponse>>? {
        return request?.searchTeamByName(teamName)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

    override fun getSearchObserver(): DisposableObserver<Response<TeamResponse>> {
        return object : DisposableObserver<Response<TeamResponse>>() {
            override fun onComplete() {
                Log.d("data-completed","Completed Fetch")
            }

            override fun onNext(t: Response<TeamResponse>) {
                val listData: ArrayList<TeamModel> = ArrayList()
                for (item in t.body()?.teams!!.indices) {
                    val dataTeamModel = TeamModel(t.body()!!.teams?.get(item)?.idTeam.toString(),
                            t.body()!!.teams?.get(item)?.teamName.toString(),
                            t.body()!!.teams?.get(item)?.teamFormed.toString(),
                            t.body()!!.teams?.get(item)?.teamBadge.toString(),
                            t.body()!!.teams?.get(item)?.teamStadium.toString(),
                            t.body()!!.teams?.get(item)?.teamDescription.toString())
                    listData.add(dataTeamModel)
                }
                view.populateTeamData(listData)
            }

            override fun onError(e: Throwable) {
                Log.e("error-search",e.message)
            }

        }
    }
}