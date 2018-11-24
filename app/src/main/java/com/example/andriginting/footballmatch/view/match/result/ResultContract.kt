package com.example.andriginting.footballmatch.view.match.result

import com.example.andriginting.footballmatch.model.LeagueResponse
import com.example.andriginting.footballmatch.model.MatchResponse
import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.model.SearchLeagueResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.DisposableObserver
import retrofit2.Response

interface ResultContract {
    interface View{
        fun populateData(data: List<PrevMatchModel>)
        fun initComponent()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
    }
    interface Presenter{
        fun getMatchByName(teamName: String): Single<Response<SearchLeagueResponse>>?
        fun getMatchObserver(): DisposableObserver<Response<SearchLeagueResponse>>
    }
}