package com.example.andriginting.footballmatch.view.detail.player

import com.example.andriginting.footballmatch.model.player.PlayerModel

interface DetailPlayerContract {
    interface View{
        fun receivedData(data: PlayerModel)
        fun setToolbarTitle(title: String)
    }
}