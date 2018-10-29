package com.example.andriginting.footballmatch.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.utils.DateFormater.Companion.dateFormater
import com.example.andriginting.footballmatch.view.match.detail.DetailMatchActivity
import org.jetbrains.anko.intentFor

class NextMatchAdapter (private val list: ArrayList<PrevMatchModel>,
                        private val context: Context):
        RecyclerView.Adapter<NextMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.next_match_item,parent,false)
        return ViewHolder(view = view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bindData(list[pos])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var clubNameHome: TextView = itemView.findViewById(R.id.text_club_name_home_next)
        private var clubNameAway: TextView = itemView.findViewById(R.id.text_club_name_away_next)
        private var date: TextView = itemView.findViewById(R.id.text_date_next_match_next)
        private var items: CardView = itemView.findViewById(R.id.item_club_match_next)


        fun bindData(data: PrevMatchModel){
            clubNameHome.text = data.homeTeam.teamName
            clubNameAway.text = data.awayTeam.teamName
            date.text = dateFormater(data.dateMatch)

            items.setOnClickListener {
                itemView.context.startActivity(
                        itemView.context.intentFor<DetailMatchActivity>(DetailMatchActivity.DETAIL_MATCH to data)
                )
            }
        }
    }

}