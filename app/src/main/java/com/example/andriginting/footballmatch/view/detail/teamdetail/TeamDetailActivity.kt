package com.example.andriginting.footballmatch.view.detail.teamdetail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.adapter.ViewPagerAdapter
import com.example.andriginting.footballmatch.model.teams.TeamModel
import com.example.andriginting.footballmatch.view.match.BasePagerView
import kotlinx.android.synthetic.main.activity_detail_match.*

import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.content_layout_detail_team.*
import org.jetbrains.anko.design.snackbar

class TeamDetailActivity : AppCompatActivity(),
        TeamDetailContract.View, BasePagerView {

    companion object {
        const val TEAM_DETAIL = "detailTeam"
    }

    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var presenter: ImpDetailTeamPresenter

    private var isFav: Boolean = false
    private var data: TeamModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        data = intent.getParcelableExtra(TEAM_DETAIL)
        presenter = ImpDetailTeamPresenter(this, applicationContext)

        pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        tablayout_teams.setupWithViewPager(viewpager_team_detail)
       isFav = presenter.favTeamState(data!!)
        setupViewPager(viewpager_team_detail)
        setTitleToolbar(data!!.clubName)
        initComponent(data!!)

    }

    override fun setTitleToolbar(title: String) {
        supportActionBar?.title = title
    }

    override fun initComponent(data: TeamModel) {
        text_club_name_detail.text = data.clubName
        text_club_stadium_name_detail.text = data.clubStadium
        text_club_year_detail.text = data.clubFormed
        Glide.with(this)
                .load(data.clubLogo)
                .into(image_club_logo_detail)
    }

    override fun setupViewPager(viewPager: ViewPager) {
        pagerAdapter.addFragment(OverviewFragment(), "Overview")
        pagerAdapter.addFragment(ListPlayerFragment(), "Players")
        viewpager_team_detail.adapter = pagerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        changeFavIcon(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                finish()
            }

            R.id.menu_fav -> {
                isFav = if (!isFav) {
                    presenter.saveTeamToDB(data!!)
                    item.setIcon(R.drawable.icon_star_white_24dp)
                    true
                } else {
                    presenter.removeTeamFromDB(data!!)
                    item.setIcon(R.drawable.icon_empty_star_white_24dp)
                    false
                }
            }
        }
        return true
    }

    override fun changeFavIcon(menu: Menu?) {
        menu?.getItem(0)?.icon =
                if (isFav) {
                    ContextCompat.getDrawable(this, R.drawable.icon_star_white_24dp)
                } else {
                    ContextCompat.getDrawable(this, R.drawable.icon_empty_star_white_24dp)
                }
    }

    override fun showSnackbar(text: String) {
        snackbar(container, text)
    }
}
