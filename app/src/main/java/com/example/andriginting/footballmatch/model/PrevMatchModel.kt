package com.example.andriginting.footballmatch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PrevMatchModel(val matchId: String,
                          val league: String,
                          val homeTeam: Team,
                          val homeStat: MatchStat,
                          val awayTeam: Team,
                          val awayStat: MatchStat,
                          val dateMatch: String,
                          val matchTime: String,
                          val banner: String) : Parcelable

@Parcelize
data class Team(val teamId: String,
                val teamName: String): Parcelable

@Parcelize
data class MatchStat(val goals: Int?,
                     val totalShots: String,
                     val yellowCard: String,
                     val redCard: String): Parcelable