package com.example.andriginting.footballmatch.match

import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.view.match.next.NextContract
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.api.dsl.xit
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import rx.observers.TestSubscriber
import kotlin.collections.ArrayList

@RunWith(MockitoJUnitRunner::class)
class NextEventTest : Spek({
    given("next match") {
        val nextView: NextContract.View = mock(NextContract.View::class.java)
        val list: ArrayList<PrevMatchModel> = ArrayList()
        val presenter: NextContract.Presenter = mock(NextContract.Presenter::class.java)

        beforeEachTest {
            MockitoAnnotations.initMocks(this)
        }

        on("Next match presenter") {
            it("should return data") {
                doNothing().`when`(presenter).getMatchDetail(4335)

            }
            it("should return null"){
                `when`(presenter.getMatchDetail(ArgumentMatchers.nullable(Int::class.java))).getMock()

            }
        }
    }
})