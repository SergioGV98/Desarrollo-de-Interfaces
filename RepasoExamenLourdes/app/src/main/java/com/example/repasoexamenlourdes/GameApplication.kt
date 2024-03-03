package com.example.repasoexamenlourdes

import android.app.Application

class GameApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Locator.initWith(this)
    }
}