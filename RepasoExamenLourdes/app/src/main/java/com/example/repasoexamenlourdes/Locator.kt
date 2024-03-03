package com.example.repasoexamenlourdes

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.moronlu18.invoice.ui.preferences.DataStorePreferencesRepository

object Locator {

    var application: Application? = null

    inline val requireApplication
        get() = application ?: error("Fallo al iniciar el initWith")

    fun initWith(application: Application){
        this.application = application
    }

    private val Context.settingsStore by preferencesDataStore(name = "info")

    val settingsPreferencesRepository by lazy {
        DataStorePreferencesRepository(requireApplication.settingsStore)
    }

}