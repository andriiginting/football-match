package com.example.andriginting.footballmatch.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.model.teams.TeamModel
import com.example.andriginting.footballmatch.view.detail.teamdetail.TeamDetailActivity
import com.example.andriginting.footballmatch.view.detail.teamdetail.TeamDetailActivity.Companion.TEAM_DETAIL

class TeamsAdapter(private val list: ArrayList<TeamModel>) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_team_layout, parent, false)
        return ViewHolder(view = view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = itemView.findViewById(R.id.image_team_logo)
        private val teamName: TextView = itemView.findViewById(R.id.text_teams_name)
        private val listTeam: LinearLayout = itemView.findViewById(R.id.list_team)

        fun bindData(data: TeamModel) {
            Glide.with(itemView.context)
                    .load(data.clubLogo)
                    .into(image)

            teamName.text = data.clubName

            listTeam.setOnClickListener {
                val intent = Intent(itemView.context, TeamDetailActivity::class.java)
                intent.putExtra(TEAM_DETAIL, data)
                itemView.context.startActivity(intent)
            }
        }
    }
}