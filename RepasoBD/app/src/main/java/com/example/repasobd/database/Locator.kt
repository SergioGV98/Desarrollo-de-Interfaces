package com.example.repasobd.database

import android.app.Application
import android.content.ContentResolver
import android.net.Uri


object Locator {
    public var application: Application? = null

    public inline val requireApplication
        get() = application ?: error("Missing call: initWith(application)")

    fun initWith(application: Application) {
        this.application = application
    }


}