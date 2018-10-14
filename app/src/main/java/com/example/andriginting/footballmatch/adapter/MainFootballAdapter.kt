package com.example.andriginting.footballmatch.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide

import com.example.andriginting.footballmatch.model.ClubModel
import com.example.andriginting.footballmatch.view.FootbalItem
import org.jetbrains.anko.AnkoContext

class MainFootballAdapter(val context: Context, val list: List<ClubModel>) : RecyclerView.Adapter<MainFootballAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(FootbalItem().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageClub = itemView.findViewById(FootbalItem.imageClubItem) as ImageView
        private val clubName = itemView.findViewById(FootbalItem.textCLubName) as TextView
        private val items = itemView.findViewById(FootbalItem.linearItem) as LinearLayout

        fun bindItem(data: ClubModel) {
            clubName.text = data.clubName
            Glide.with(itemView.context)
                    .load(data.clubLogo)
                    .into(imageClub)
            items.setOnClickListener {

            }
        }
    }
}