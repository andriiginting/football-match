package com.example.andriginting.footballmatch.network

import com.example.andriginting.footballmatch.model.LeagueResponse
import com.example.andriginting.footballmatch.model.teams.TeamResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkInterface {

    @GET("eventspastleague.php")
    fun getPreviousMatch(@Query("id") leagueId: Int): Observable<Response<LeagueResponse>>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") leagueId: Int): Observable<Response<LeagueResponse>>

    @GET("lookupteam.php")
    fun getDetailTeam(@Query("id") teamId: String): Observable<Response<TeamResponse>>

    @GET()
    fun getDetailPlayer(@Query("id") playerId: String)
}