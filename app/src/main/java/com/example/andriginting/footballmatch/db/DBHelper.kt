package com.example.andriginting.footballmatch.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.AWAY_RED_CARD
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.AWAY_SCORE
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.AWAY_TEAM_ID
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.AWAY_TEAM_NAME
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.AWAY_TOTAL_SHOT
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.AWAY_YELLOW_CARD
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.HOME_RED_CARD
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.HOME_SCORE
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.HOME_TEAM_ID
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.HOME_TEAM_NAME
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.HOME_TOTAL_SHOT
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.HOME_YELLOW_CARD
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.ID
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.MATCH_BANNER
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.MATCH_DATE
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.MATCH_ID
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.MATCH_LEAGUE
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.MATCH_TIME
import com.example.andriginting.footballmatch.db.FootballMatchDB.Companion.TABLE_MATCH_NAME
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TABLE_TEAM_NAME
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TEAMS_DESCRIPTIONS
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TEAMS_FORMER
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TEAMS_ID
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TEAMS_NAME
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TEAMS_STADIUM
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion.TEAM_LOGO
import com.example.andriginting.footballmatch.db.FootballTeamDB.Companion._ID
import org.jetbrains.anko.db.*

class DBHelper(context: Context):
        ManagedSQLiteOpenHelper(context,"football_match_fav.db",null,1) {

    companion object {
        private var instance: DBHelper? = null

        @Synchronized
    fun getDbInstance(context: Context): DBHelper{
            if(instance == null){
                instance = DBHelper(context)
            }

            return instance as DBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(TABLE_MATCH_NAME,true,
                ID to INTEGER + PRIMARY_KEY+ AUTOINCREMENT,
                MATCH_ID to TEXT + UNIQUE,
                MATCH_LEAGUE to TEXT,
                HOME_TEAM_ID to TEXT,
                HOME_TEAM_NAME to TEXT,
                HOME_SCORE to TEXT,
                HOME_TOTAL_SHOT to TEXT,
                HOME_YELLOW_CARD to TEXT,
                HOME_RED_CARD to TEXT,
                AWAY_TEAM_ID to TEXT,
                AWAY_TEAM_NAME to TEXT,
                AWAY_SCORE to TEXT,
                AWAY_TOTAL_SHOT to TEXT,
                AWAY_YELLOW_CARD to TEXT,
                AWAY_RED_CARD to TEXT,
                MATCH_DATE to TEXT,
                MATCH_TIME to TEXT,
                MATCH_BANNER to TEXT)

        db?.createTable(TABLE_TEAM_NAME, true,
                _ID to INTEGER + PRIMARY_KEY+ AUTOINCREMENT,
                TEAMS_ID to TEXT + UNIQUE,
                TEAMS_NAME to TEXT,
                TEAMS_FORMER to TEXT,
                TEAM_LOGO to TEXT,
                TEAMS_STADIUM to TEXT,
                TEAMS_DESCRIPTIONS to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(TABLE_MATCH_NAME,true)
        db?.dropTable(TABLE_TEAM_NAME,true)
    }
}