package com.example.andriginting.footballmatch.view.match.detail
import android.view.Menu
import com.example.andriginting.footballmatch.model.PrevMatchModel

interface DetailMatchContract {
    interface View {
        fun setTitleOnToolbar(homeTeam: String, AwayTeam: String)
        fun setSubTitleOnToolbar(dateMatch: String)
        fun setHomeClubLogo(homeBadge: String)
        fun setAwayClubLogo(AwayBadge: String)
        fun setDetailMatch(data: PrevMatchModel)
        fun showWarnFromNextMatch(data: PrevMatchModel)
        fun showSnackbar(text: String)
        fun changeFavIcon(menu: Menu?)
    }
    interface Presenter{
        fun getHomeBadge(homeTeamId: String)
        fun getAwayBadge(awayTeamId: String)
        fun saveMatchToDB(data: PrevMatchModel)
        fun removeMatchFromDB(data: PrevMatchModel)
        fun favMatchState(data: PrevMatchModel): Boolean
    }
}