package com.example.andriginting.footballmatch.match

import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.view.match.prev.ImpPreviousPresenter
import com.example.andriginting.footballmatch.view.match.prev.PrevContract
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.ArrayList

class PrevEventTest {
    @Mock
    private lateinit var nextView: PrevContract.View
    @Mock
    private lateinit var presenter: ImpPreviousPresenter
    @Mock
    lateinit var model: PrevMatchModel
    lateinit var list: ArrayList<PrevMatchModel>

    @Before
    fun setupTest() {
        model = Mockito.mock(PrevMatchModel::class.java)
        nextView = Mockito.mock(PrevContract.View::class.java)
        list = ArrayList()
        presenter = ImpPreviousPresenter(view = nextView, list = list)
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getNextMatchDetailSpec_hasNoData() {

        val leagueId = 4335
        `when`(presenter.getMatchDetail(leagueId)).thenCallRealMethod()

        Mockito.verify(nextView, Mockito.never()).showLoadingIndicator()
        Mockito.verify(nextView, Mockito.never()).showFootBallSchedule(list)
        Mockito.verify(nextView, Mockito.never()).hidLoadingIndicator()

    }

}