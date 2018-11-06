package com.example.andriginting.footballmatch.model.player

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerModel(val playerName: String,
                       val playerBanner: String,
                       val playerImage: String,
                       val playerWeight: String,
                       val playerHeight: String,
                       val playerPosition: String,
                       val playerDetails: String): Parcelable