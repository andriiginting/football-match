package com.example.andriginting.footballmatch.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.model.MatchResponse
import com.example.andriginting.footballmatch.utils.DateFormater
import com.example.andriginting.footballmatch.view.match.detail.DetailMatchActivity
import org.jetbrains.anko.intentFor

class SearchAdapter(val list: List<MatchResponse>,
                    val context: Context): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.previous_match_item, parent, false)
        return ViewHolder(view = view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bindData(list[pos])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var scoreHome: TextView = itemView.findViewById(R.id.text_score_home)
        private var scoreAway: TextView = itemView.findViewById(R.id.text_score_away)
        private var clubNameHome: TextView = itemView.findViewById(R.id.text_club_name_home)
        private var clubNameAway: TextView = itemView.findViewById(R.id.text_club_name_away)
        private var matchDate: TextView = itemView.findViewById(R.id.text_date_event_past)
        private var matchTime: TextView = itemView.findViewById(R.id.text_time_event_past)
        private var items: CardView = itemView.findViewById(R.id.item_club_match)


        fun bindData(data: MatchResponse) {
            scoreHome.text = data.intHomeScore.toString()
            scoreAway.text = data.intAwayScore.toString()
            clubNameHome.text = data.strHomeTeam
            clubNameAway.text = data.strAwayTeam

            items.setOnClickListener {
                itemView.context.startActivity(itemView.context.intentFor<DetailMatchActivity>(DetailMatchActivity.DETAIL_MATCH to data))
            }
        }
    }
}