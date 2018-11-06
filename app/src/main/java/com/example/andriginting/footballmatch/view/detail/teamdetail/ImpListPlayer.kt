package com.example.andriginting.footballmatch.view.detail.teamdetail

import android.annotation.SuppressLint
import com.example.andriginting.footballmatch.model.player.PlayerModel
import com.example.andriginting.footballmatch.network.NetworkInterface
import com.example.andriginting.footballmatch.network.NetworkModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImpListPlayer(private val view: ListPlayerContract.View) : ListPlayerContract.Presenter {

    private val request = NetworkModule()
            .getRetrofitClient()?.create(NetworkInterface::class.java)

    @SuppressLint("CheckResult")
    override fun getListPlayer(teamId: Int, list: ArrayList<PlayerModel>) {
        view.showLoadingIndicator()
        request?.getListPlayer(teamId)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ response ->
                    when {
                        response.isSuccessful -> {
                            val res = response.body()?.listPlayer
                            for (items in res!!.indices) {
                                list.add(PlayerModel(res[items].playerName.toString(),
                                        res[items].playerBanner.toString(),
                                        res[items].playerImage.toString(),
                                        res[items].playerWeight.toString(),
                                        res[items].playerHeight.toString(),
                                        res[items].playerPosition.toString(),
                                        res[items].playerDetail.toString()))
                                view.populatePlayerData(list)
                            }
                        }
                    }
                    view.hideLoadingIndicator()
                }, { error ->
                    view.hideLoadingIndicator()
                    error.printStackTrace()
                })

    }
}