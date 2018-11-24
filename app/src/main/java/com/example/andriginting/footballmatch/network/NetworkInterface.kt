package com.example.andriginting.footballmatch.network

import com.example.andriginting.footballmatch.model.LeagueResponse
import com.example.andriginting.footballmatch.model.SearchLeagueResponse
import com.example.andriginting.footballmatch.model.league.ListLeagueResponse
import com.example.andriginting.footballmatch.model.player.ListPlayerResponse
import com.example.andriginting.footballmatch.model.teams.TeamResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkInterface {

    @GET("eventspastleague.php")
    fun getPreviousMatch(@Query("id") leagueId: Int): Observable<Response<LeagueResponse>>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") leagueId: Int): Observable<Response<LeagueResponse>>

    @GET("lookup_all_teams.php")
    fun getDetailTeam(@Query("id") teamId: Int): Observable<Response<TeamResponse>>

    @GET("all_leagues.php")
    fun getAllListOfLeague(): Observable<Response<ListLeagueResponse>>

    @GET("lookup_all_players.php")
    fun getListPlayer(@Query("id") teamId: Int): Observable<Response<ListPlayerResponse>>

    @GET("searchteams.php")
    fun searchTeamByName(@Query("t") teamName: String):
            Single<Response<TeamResponse>>

    @GET("searchevents.php")
    fun searchEventsByEventsName(@Query("e") eventName: String):
            Single<Response<SearchLeagueResponse>>
}