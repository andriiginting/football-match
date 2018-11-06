package com.example.andriginting.footballmatch.view.teams

import com.example.andriginting.footballmatch.model.teams.TeamModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.collections.ArrayList

class ImpTeamPresenterTest {

    @Mock
    var presenter: ImpTeamPresenter? = null

    @Mock
    var view: TeamContract.View? = null

    @Mock
    var listTeam: ArrayList<TeamModel>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        view = Mockito.mock(TeamContract.View::class.java)
        listTeam = ArrayList()

        presenter = view?.let { ImpTeamPresenter(it) }
    }

    @Test
    fun testGetTeam_shouldReturn_200() {
        val sampleId = 4335

        `when`(listTeam?.let { presenter?.getListOfTeam(sampleId, it) })
                .thenReturn(listTeam)
        verify(view)?.hideLoadingIndicator()
    }

}