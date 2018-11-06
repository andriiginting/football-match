package com.example.andriginting.footballmatch.view.detail.teamdetail

import android.view.Menu
import com.example.andriginting.footballmatch.model.teams.TeamModel

interface TeamDetailContract {
    interface View {
        fun setTitleToolbar(title: String)
        fun initComponent(data: TeamModel)
        fun showSnackbar(text: String)
        fun changeFavIcon(menu: Menu?)
    }
    interface Presenter{
        fun saveTeamToDB(data: TeamModel)
        fun removeTeamFromDB(data: TeamModel)
        fun favTeamState(data: TeamModel): Boolean
    }
}