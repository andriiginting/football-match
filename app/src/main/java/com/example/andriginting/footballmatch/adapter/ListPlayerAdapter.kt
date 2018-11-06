package com.example.andriginting.footballmatch.adapter

import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.model.player.PlayerModel
import com.example.andriginting.footballmatch.view.detail.player.PlayerActivity
import com.example.andriginting.footballmatch.view.detail.player.PlayerActivity.Companion.DETAIL_PLAYER

class ListPlayerAdapter(private val list: ArrayList<PlayerModel>) : RecyclerView.Adapter<ListPlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.content_item_list_player, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = itemView.findViewById(R.id.image_player)
        private val playerName: TextView = itemView.findViewById(R.id.text_player_name)
        private val playerPosition: TextView = itemView.findViewById(R.id.text_player_position)
        private val list: ConstraintLayout = itemView.findViewById(R.id.list_player)

        fun bindData(data: PlayerModel) {
            Glide.with(itemView.context)
                    .load(data.playerImage)
                    .into(image)

            playerName.text = data.playerName
            playerPosition.text = data.playerPosition

            list.setOnClickListener {
                val intent = Intent(itemView.context, PlayerActivity::class.java)
                intent.putExtra(DETAIL_PLAYER, data)
                itemView.context.startActivity(intent)
            }
        }
    }
}