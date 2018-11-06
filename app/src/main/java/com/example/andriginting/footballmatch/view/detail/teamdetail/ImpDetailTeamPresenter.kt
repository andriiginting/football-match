package com.example.andriginting.footballmatch.view.detail.teamdetail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.example.andriginting.footballmatch.db.FootballTeamDB
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TABLE_TEAM_NAME
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TEAMS_DESCRIPTIONS
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TEAMS_FORMER
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TEAMS_ID
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TEAMS_NAME
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TEAMS_STADIUM
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TEAM_LOGO
import com.example.andriginting.footballmatch.extension.databaseHelper
import com.example.andriginting.footballmatch.model.teams.TeamModel
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ImpDetailTeamPresenter(private val view: TeamDetailContract.View,
                             private val context: Context) : TeamDetailContract.Presenter {

    override fun saveTeamToDB(data: TeamModel) {
        try {
            context.databaseHelper.use {
                insert(TABLE_TEAM_NAME,
                        TEAMS_ID to data.teamId,
                        TEAMS_NAME to data.clubName,
                        TEAMS_FORMER to data.clubFormed,
                        TEAM_LOGO to data.clubLogo,
                        TEAMS_STADIUM to data.clubStadium,
                        TEAMS_DESCRIPTIONS to data.clubDescriptions)
            }
            view.showSnackbar("Teams Saved")
        } catch (err: SQLiteConstraintException) {
            view.showSnackbar("Something Wrong!")
        }
    }

    override fun removeTeamFromDB(data: TeamModel) {
        try {
            context.databaseHelper.use {
                delete(TABLE_TEAM_NAME, "TEAM_ID = {id}", "id" to data.teamId)
            }
            view.showSnackbar("Teams Removed")

        } catch (err: SQLiteConstraintException) {
            view.showSnackbar("Something Wrong!")
        }
    }

    override fun favTeamState(data: TeamModel): Boolean {
        var fav = false
        context.databaseHelper.use {
            val res = select(TABLE_TEAM_NAME).whereArgs("(TEAM_ID = {id})", "id" to data.teamId)
            val favTeam = res.parseList(classParser<FootballTeamDB>())
            fav = favTeam.isNotEmpty()
        }
        return fav
    }
}