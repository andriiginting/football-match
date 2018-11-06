package com.example.andriginting.footballmatch.db

class FootballTeamDB(val id: Long?,
                     val teamId: String,
                     val clubName: String,
                     val clubFormed: String,
                     val clubLogo: String,
                     val clubStadium: String,
                     val clubDescriptions: String) {
    companion object {
        const val TABLE_TEAM_NAME = "TABLE_TEAM"
        const val _ID: String = "_ID"
        const val TEAMS_ID: String = "TEAM_ID"
        const val TEAMS_NAME: String = "TEAMS_NAME"
        const val TEAMS_FORMER: String = "TEAMS_FORMER"
        const val TEAM_LOGO: String = "TEAM_LOGO"
        const val TEAMS_STADIUM: String = "TEAMS_STADIUM"
        const val TEAMS_DESCRIPTIONS: String = "TEAMS_DESCRIPTIONS"
    }
}