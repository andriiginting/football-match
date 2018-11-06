package com.example.andriginting.footballmatch.model.teams

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClubModel(val clubName: String,
                     val clubLogo: Int,
                     val clubDetail: String) : Parcelable

@Parcelize
data class TeamModel(val teamId: String,
                     val clubName: String,
                     val clubFormed: String,
                     val clubLogo: String,
                     val clubStadium: String,
                     val clubDescriptions: String) : Parcelable