package com.example.repasoexamenlourdes

import android.app.Application

object Locator {

    var application: Application? = null

    inline val requireApplication
        get() = application ?: error("Fallo al iniciar el initWith")

    fun initWith(application: Application){
        this.application = application
    }

}