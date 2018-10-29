package com.example.andriginting.footballmatch.db


class FootballDB(val id: Long?,
                 val matchId: String?,
                 val league: String?,
                 val homeTeamId: String?,
                 val homeTeamName: String?,
                 val homeGoals: String?,
                 val homeTotalShots: String?,
                 val homeYellowCard: String?,
                 val homeRedCard: String?,
                 val awayTeamId: String?,
                 val awayTeamName: String?,
                 val awayGoals: String?,
                 val awayTotalShots: String?,
                 val awayYellowCard: String?,
                 val awayRedCard: String?,
                 val dateMatch: String?,
                 val banner: String?) {

    companion object {
        const val TABLE_MATCH_NAME: String = "TABLE_MATCH_FAVORITE"
        const val ID: String = "_ID"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_LEAGUE: String = "MATCH_LEAGUE"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val HOME_TOTAL_SHOT: String = "HOME_TOTAL_SHOT"
        const val HOME_YELLOW_CARD: String = "HOME_YELLOW_CARD"
        const val HOME_RED_CARD: String = "HOME_RED_CARD"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val AWAY_TOTAL_SHOT: String = "AWAY_TOTAL_SHOT"
        const val AWAY_YELLOW_CARD: String = "AWAY_YELLOW_CARD"
        const val AWAY_RED_CARD: String = "AWAY_RED_CARD"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val MATCH_BANNER: String = "MATCH_BANNER"
    }
}