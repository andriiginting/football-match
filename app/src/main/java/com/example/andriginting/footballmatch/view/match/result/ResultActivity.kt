package com.example.andriginting.footballmatch.view.match.result

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.support.v7.widget.SearchView
import android.view.View
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.adapter.PreviousMatchAdapter
import com.example.andriginting.footballmatch.extension.invisible
import com.example.andriginting.footballmatch.extension.visible
import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.model.SearchLeagueResponse
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.functions.Function
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.content_result.*
import retrofit2.Response
import java.util.concurrent.TimeUnit


class ResultActivity : AppCompatActivity(), ResultContract.View{


    private var adapter: PreviousMatchAdapter? = null
    private var list: ArrayList<PrevMatchModel> = ArrayList()
    private lateinit var presenter: ImpResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = ImpResultPresenter(this)

        adapter = PreviousMatchAdapter(list, applicationContext)
        initComponent()
    }

    override fun populateData(data: List<PrevMatchModel>) {
        list.clear()
        list.addAll(data)
        adapter?.notifyDataSetChanged()
    }

    override fun initComponent() {
        recycler_result_match_search.layoutManager = LinearLayoutManager(this)
        recycler_result_match_search.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                finish()
            }
        }
        return true
    }

    override fun showLoadingIndicator() {
        progress_result.visible()
    }

    override fun hideLoadingIndicator() {
        progress_result.invisible()
    }

    @SuppressLint("CheckResult")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu?.findItem(R.id.search_menu)
        val searchView = searchItem?.actionView as SearchView
        searchItem.expandActionView()
        searchView.queryHint = getString(R.string.find_match)

        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    subscriber.onNext(newText!!)
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    subscriber.onNext(query!!)
                    return true
                }
            })
        })
                .map { text -> text.toLowerCase().trim() }
                .debounce(400, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .filter { text -> text.isNotBlank() }
                .switchMapSingle(Function<String, Single<Response<SearchLeagueResponse>>> { query ->
                    list.clear()
                    return@Function presenter.getMatchByName(query)
                })
                .subscribeWith(presenter.getMatchObserver())

        return true
    }
}
