package com.example.andriginting.footballmatch.model.teams

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClubModel(val clubName: String, val clubLogo: Int, val clubDetail: String): Parcelable