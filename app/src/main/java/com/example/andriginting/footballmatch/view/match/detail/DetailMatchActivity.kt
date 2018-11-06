package com.example.andriginting.footballmatch.view.match.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.R.drawable.icon_star_white_24dp
import com.example.andriginting.footballmatch.extension.invisible
import com.example.andriginting.footballmatch.extension.visible
import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.utils.DateFormater.Companion.dateFormater
import com.example.andriginting.footballmatch.utils.StringsUtils.Companion.count
import com.example.andriginting.footballmatch.utils.StringsUtils.Companion.transformNull

import kotlinx.android.synthetic.main.activity_detail_match.*
import kotlinx.android.synthetic.main.content_detail_match.*
import kotlinx.android.synthetic.main.content_match_stats_layout.*
import kotlinx.android.synthetic.main.detail_match_team_layout.*
import org.jetbrains.anko.design.snackbar

class DetailMatchActivity : AppCompatActivity(), DetailMatchContract.View {


    companion object {
        const val DETAIL_MATCH = "detailMatch"
    }

    lateinit var presenter: ImpDetailPresenter
    private var data: PrevMatchModel? = null

    private var isFav: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        data = intent.getParcelableExtra(DETAIL_MATCH)
        setTitleOnToolbar(data?.homeTeam?.teamName.toString(), data?.awayTeam?.teamName.toString())
        setSubTitleOnToolbar(dateMatch = data?.dateMatch.toString())

        presenter = ImpDetailPresenter(this, applicationContext)
        presenter.getHomeBadge(data?.homeTeam?.teamId!!.toInt())
        presenter.getAwayBadge(data?.awayTeam?.teamId!!.toInt())
        isFav = presenter.favMatchState(data!!)

        setDetailMatch(data!!)
        showWarnFromNextMatch(data!!)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                finish()
            }
            R.id.menu_fav -> {
                isFav = if (!isFav) {
                    presenter.saveMatchToDB(data = data!!)
                    item.setIcon(R.drawable.icon_star_white_24dp)
                    true
                } else {
                    presenter.removeMatchFromDB(data!!)
                    item.setIcon(R.drawable.icon_empty_star_white_24dp)
                    false
                }
            }
        }
        return true
    }

    override fun setTitleOnToolbar(homeTeam: String, AwayTeam: String) {
        supportActionBar?.title = "$homeTeam VS $AwayTeam"
    }

    override fun setSubTitleOnToolbar(dateMatch: String) {
        supportActionBar?.subtitle = dateFormater(dateMatch)
    }

    override fun setHomeClubLogo(homeBadge: String) {
        Glide.with(this)
                .load(homeBadge)
                .into(image_badge_home_team)
    }

    override fun setAwayClubLogo(AwayBadge: String) {
        Glide.with(this)
                .load(AwayBadge)
                .into(image_badge_away_team)
    }

    override fun setDetailMatch(data: PrevMatchModel) {
        text_name_home_detail.text = data.homeTeam.teamName
        text_name_away_detail.text = data.awayTeam.teamName
        text_score_home_detail.text = data.homeStat.goals?.let { transformNull(it) }
        text_score_away_detail.text = data.awayStat.goals?.let { transformNull(it) }

        home_goals_count.text = data.homeStat.goals.toString()
        away_goals_count.text = data.awayStat.goals.toString()
        home_shots_count.text = count(data.homeStat.totalShots).toString()
        away_shots_count.text = count(data.awayStat.totalShots).toString()
        home_yellow_card.text = count(data.homeStat.yellowCard).toString()
        away_yellow_card.text = count(data.awayStat.yellowCard).toString()
        home_red_card.text = count(data.homeStat.redCard).toString()
        away_red_card.text = count(data.awayStat.redCard).toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        changeFavIcon(menu)
        return true
    }


    @SuppressLint("SetTextI18n")
    override fun showWarnFromNextMatch(data: PrevMatchModel) {
        if (data.homeStat.goals == null && data.awayStat.goals == null) {
            layout_content_stat.invisible()
            warn_next_match.visible()
            warn_next_match.text = "The match will be held on ${dateFormater(data.dateMatch)}"
        } else {
            layout_content_stat.visible()
            warn_next_match.invisible()
        }
    }

    override fun changeFavIcon(menu: Menu?) {
        menu?.getItem(0)?.icon =
                if (isFav) {
                    ContextCompat.getDrawable(this, icon_star_white_24dp)
                } else {
                    ContextCompat.getDrawable(this, R.drawable.icon_empty_star_white_24dp)
                }
    }

    override fun showSnackbar(text: String) {
        snackbar(container_detail, text)
    }
}
