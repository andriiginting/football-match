package com.example.andriginting.footballmatch.favorite

import com.example.andriginting.footballmatch.db.FootballMatchDB
import com.example.andriginting.footballmatch.db.FootballTeamDB
import com.example.andriginting.footballmatch.view.match.fav.FavoriteMatchContract
import com.example.andriginting.footballmatch.view.match.fav.FavoriteTeamContract
import junit.framework.Assert.assertNotNull
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.api.dsl.xit
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteSpek : Spek({
    given("Favorite match and team") {
        beforeEachTest {
            MockitoAnnotations.initMocks(this)
        }

        val favoriteMatchPresenter: FavoriteMatchContract.Presenter =
                mock(FavoriteMatchContract.Presenter::class.java)

        val favoriteTeamPresenter: FavoriteTeamContract.Presenter =
                mock(FavoriteTeamContract.Presenter::class.java)


        on("init match presenter") {
            it("should populate favorite match with zero interaction") {
                doAnswer {
                    verifyZeroInteractions()
                    return@doAnswer null
                }.`when`(favoriteMatchPresenter).getFavoriteMatch()
            }
        }

        on("init team presenter") {
            it("should populate favorite match with zero interaction") {
                doAnswer {
                    return@doAnswer null
                }.`when`(favoriteTeamPresenter).getFavoriteTeam()

                verify(favoriteTeamPresenter, times(0)).getFavoriteTeam()
            }
        }
    }
})