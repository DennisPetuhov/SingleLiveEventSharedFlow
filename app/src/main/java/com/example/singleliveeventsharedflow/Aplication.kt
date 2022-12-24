package com.example.singleliveeventsharedflow

import android.app.Application

class Aplication:Application() {


    private val uploadAuction: SingleLiveEvent<String> = SingleLiveEvent()

    override fun onCreate() {
        super.onCreate()

    }
}