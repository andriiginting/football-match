package com.example.andriginting.footballmatch.match


import com.example.andriginting.footballmatch.model.teams.TeamModel
import com.example.andriginting.footballmatch.view.teams.TeamContract
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MatchSpek: Spek({
    given("Team in league"){

        beforeEachTest {
            MockitoAnnotations.initMocks(this)
        }

        val view: TeamContract.View = mock(TeamContract.View::class.java)
        val presenter: TeamContract.Presenter = mock(TeamContract.Presenter::class.java)
        val model: TeamModel? = null

        val listTeam: ArrayList<TeamModel>? = ArrayList()

        on("init presenter"){
            it("should show loading indicator"){
                verifyZeroInteractions(view)
            }

            it("should return list of data"){
                `when`(listTeam?.let { presenter.getListOfTeam(4335, it) })
                        .thenReturn(listTeam)
            }

            it("should return team data"){
                model?.let { verify(view)?.populateTeamData(it) }
            }

        }
    }
})