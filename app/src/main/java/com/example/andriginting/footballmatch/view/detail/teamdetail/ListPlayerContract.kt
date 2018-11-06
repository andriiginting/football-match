package com.example.andriginting.footballmatch.view.detail.teamdetail

import com.example.andriginting.footballmatch.model.player.PlayerModel

interface ListPlayerContract {
    interface View{
        fun initComponent()
        fun populatePlayerData(data: ArrayList<PlayerModel>)
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
    }

    interface Presenter{
        fun getListPlayer(teamId: Int, list: ArrayList<PlayerModel>)
    }
}