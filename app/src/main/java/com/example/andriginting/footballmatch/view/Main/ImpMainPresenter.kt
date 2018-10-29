package com.example.andriginting.footballmatch.view.Main

import android.content.Context
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.model.ClubModel

class ImpMainPresenter(private val view: MainContract.View,
                       private val context: Context,
                       private val list: ArrayList<ClubModel>) : MainContract.Presenter {

    override fun addClub(): ArrayList<ClubModel> {
        val icon = context.resources.obtainTypedArray(R.array.club_logo)
        val name = context.resources.getStringArray(R.array.club_name)
        val clubDescription = context.resources.getStringArray(R.array.club_description)
        for (items in name.indices) {
            list.add(ClubModel(name[items],
                    icon.getResourceId(items, -1),
                    clubDescription[items]))
        }
        icon.recycle()
        view.hideLoadingIndicator()
        return list
    }

}