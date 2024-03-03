package com.example.repasobd

import android.app.Application
import com.example.repasobd.database.Locator

class BookApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Locator.initWith(this)
    }
}