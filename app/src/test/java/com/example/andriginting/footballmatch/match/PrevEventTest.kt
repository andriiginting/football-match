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
    lateinit var nextView: PrevContract.View
    @Mock
    lateinit var presenter: ImpPreviousPresenter
    @Mock
    lateinit var model: PrevMatchModel
    @Mock
    lateinit var list: ArrayList<PrevMatchModel>

    @Before
    fun setupTest() {
        MockitoAnnotations.initMocks(this)
        model = Mockito.mock(PrevMatchModel::class.java)
        nextView = Mockito.mock(PrevContract.View::class.java)
        list = ArrayList()
        presenter = ImpPreviousPresenter(view = nextView, list = list)
    }

    @Test
    fun getNextMatchDetailSpec_hasNoData() {
        `when`(presenter.getMatchDetail(0)).thenReturn(null)

        Mockito.verify(nextView).showLoadingIndicator()
        Mockito.verify(nextView).showFootBallSchedule(list)
        Mockito.verify(nextView).hidLoadingIndicator()
    }

}