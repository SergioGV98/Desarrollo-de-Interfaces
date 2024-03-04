package com.example.repasoexamenlourdes2

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.repasoexamenlourdes2.Preferences.DataStorePreferencesRepository

object Locator {

    var application: Application? = null

    inline val requestApplication
        get() = application ?: error("Missing call: initWith(application)")

    fun initWith(application: Application){
        this.application = application
    }

    private val Context.userStore by preferencesDataStore(name = "user_preferences")
    val userPreferencesRepository by lazy {
        DataStorePreferencesRepository(requestApplication.userStore)
    }



}