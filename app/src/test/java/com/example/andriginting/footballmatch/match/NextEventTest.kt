package com.example.andriginting.footballmatch.match

import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.view.match.next.ImpNextPresenter
import com.example.andriginting.footballmatch.view.match.next.NextContract
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class NextEventTest {

    @Mock
    private lateinit var nextView: NextContract.View
    @Mock
    private lateinit var presenter: ImpNextPresenter

    lateinit var model: PrevMatchModel
    lateinit var list: ArrayList<PrevMatchModel>


    @Before
    fun setupTest() {
        model = Mockito.mock(PrevMatchModel::class.java)
        nextView = Mockito.mock(NextContract.View::class.java)

        list = ArrayList()
        presenter = ImpNextPresenter(view = nextView,list = list)
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getNextMatchDetailSpec_hasNoData() {
        presenter.getMatchDetail(4335)

        Mockito.verify(nextView,Mockito.never()).showLoadingIndicator()
        Mockito.verify(nextView,Mockito.never()).showFootBallSchedule(list)
        Mockito.verify(nextView,Mockito.never()).hidLoadingIndicator()
    }

}