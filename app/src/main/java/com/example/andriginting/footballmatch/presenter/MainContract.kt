package com.example.andriginting.footballmatch.presenter

interface MainContract {
    interface View{
        fun initComponent()
    }

    interface Presenter{
        fun addClub()
    }
}