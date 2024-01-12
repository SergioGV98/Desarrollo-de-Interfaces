package com.moronlu18.invoice

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.moronlu18.invoice.data.userpreferences.DataStorePreferencesRepository
import com.moronlu18.invoice.data.userpreferences.UserPreferencesRepository

object Locator {
    private var application: Application?=null

    private inline val requireApplication
        get() = application ?: error("Missing call: initWith(Application)")

    fun initWith(application: Application){
        this.application = application
    }

    private val Context.userStore by preferencesDataStore(name = "user_preferences")
    private val Context.settingsStore by preferencesDataStore(name = "settings")

    val userPreferencesRepository by lazy {
        UserPreferencesRepository(requireApplication.userStore)
    }

    val settingsPreferencesRepository by lazy {
        DataStorePreferencesRepository(requireApplication.settingsStore)
    }
}