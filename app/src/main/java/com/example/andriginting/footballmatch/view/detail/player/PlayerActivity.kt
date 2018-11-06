package com.example.andriginting.footballmatch.view.detail.player

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.model.player.PlayerModel

import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.content_player.*

class PlayerActivity : AppCompatActivity(), DetailPlayerContract.View {


    companion object {
        const val DETAIL_PLAYER = "detailPlayer"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data = intent.getParcelableExtra<PlayerModel>(DETAIL_PLAYER)
        setToolbarTitle(data.playerName)
        receivedData(data)
    }

    override fun receivedData(data: PlayerModel) {
        Glide.with(this)
                .load(data.playerBanner)
                .into(image_player)

        text_player_weight.text = data.playerWeight
        text_player_height.text = data.playerHeight
        text_player_position_detail.text = data.playerPosition
        text_player_description_detail.text = data.playerDetails
    }

    override fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                finish()
            }
        }
        return true
    }
}
