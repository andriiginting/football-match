package com.example.andriginting.footballmatch.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.db.FootballMatchDB
import com.example.andriginting.footballmatch.model.MatchStat
import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.model.Team
import com.example.andriginting.footballmatch.utils.DateFormater.Companion.dateFormater
import com.example.andriginting.footballmatch.utils.StringsUtils.Companion.transformNull
import com.example.andriginting.footballmatch.view.match.detail.DetailMatchActivity
import org.jetbrains.anko.intentFor

class FavoriteMatchAdapter(private val list: ArrayList<FootballMatchDB>,
                           private val context: Context) : RecyclerView.Adapter<FavoriteMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.favorite_match_item, parent, false)
        return FavoriteMatchAdapter.ViewHolder(view = view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindData(list[p1])
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var scoreHome: TextView = itemView.findViewById(R.id.text_score_home_fav)
        private var scoreAway: TextView = itemView.findViewById(R.id.text_score_away_fav)
        private var clubNameHome: TextView = itemView.findViewById(R.id.text_club_name_home_fav)
        private var clubNameAway: TextView = itemView.findViewById(R.id.text_club_name_away_fav)
        private var dateFav: TextView = itemView.findViewById(R.id.text_fav_date)
        private var items: LinearLayout = itemView.findViewById(R.id.item_fav)


        fun bindData(data: FootballMatchDB) {
            scoreHome.text = data.homeGoals?.toInt()?.let { transformNull(it) }
            scoreAway.text = data.awayGoals?.toInt()?.let { transformNull(it) }
            clubNameHome.text = data.homeTeamName
            clubNameAway.text = data.awayTeamName
            dateFav.text = data.dateMatch?.let { dateFormater(it) }

            val matchData = PrevMatchModel(
                    data.matchId.toString(),
                    data.league.toString(),
                    Team(data.homeTeamId.toString(), data.homeTeamName.toString()),
                    MatchStat(data.homeGoals?.toInt(),
                            data.homeTotalShots.toString(), data.homeYellowCard.toString(),
                            data.homeRedCard.toString()),
                    Team(data.awayTeamId.toString(), data.awayTeamName.toString()),
                    MatchStat(data.awayGoals?.toInt(),
                            data.awayTotalShots.toString(), data.awayYellowCard.toString(),
                            data.awayRedCard.toString()),
                    data.dateMatch.toString(),
                    data.timeMatch.toString(),
                    data.banner.toString())

            items.setOnClickListener {
                itemView.context.startActivity(
                        itemView.context.intentFor<DetailMatchActivity>(DetailMatchActivity.DETAIL_MATCH to matchData))
            }
        }
    }
}