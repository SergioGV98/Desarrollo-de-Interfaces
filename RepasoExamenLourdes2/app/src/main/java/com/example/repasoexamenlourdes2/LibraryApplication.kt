package com.example.repasoexamenlourdes2

import android.app.Application

class LibraryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Locator.initWith(this)
    }

}