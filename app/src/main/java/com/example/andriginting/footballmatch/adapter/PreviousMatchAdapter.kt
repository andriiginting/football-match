package com.example.andriginting.footballmatch.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.view.detail.DetailActivity
import com.example.andriginting.footballmatch.view.match.detail.DetailMatchActivity
import com.example.andriginting.footballmatch.view.match.detail.DetailMatchActivity.Companion.DETAIL_MATCH
import org.jetbrains.anko.intentFor

class PreviousMatchAdapter(private val list: ArrayList<PrevMatchModel>,
                           private val context: Context):
        RecyclerView.Adapter<PreviousMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.previous_match_item,parent,false)
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
        private var leaguePoster: ImageView = itemView.findViewById(R.id.image_league_poster)
        private var items: CardView = itemView.findViewById(R.id.item_club_match)


        fun bindData(data: PrevMatchModel){
            scoreHome.text = data.homeStat.goals.toString()
            scoreAway.text = data.awayStat.goals.toString()
            clubNameHome.text = data.homeTeam.teamName
            clubNameAway.text = data.awayTeam.teamName

            Glide.with(itemView.context).
                    load(data.banner).
                    into(leaguePoster)

            items.setOnClickListener {
                itemView.context.startActivity(itemView.context.intentFor<DetailMatchActivity>(DETAIL_MATCH to data))
            }
        }
    }

}