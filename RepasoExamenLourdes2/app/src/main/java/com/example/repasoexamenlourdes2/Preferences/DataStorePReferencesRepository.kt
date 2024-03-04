package com.example.repasoexamenlourdes2.Preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DataStorePreferencesRepository(private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>) :
    PreferenceDataStore() {
    companion object UserStoreKeys{
        private val ID = stringPreferencesKey("id")
    }

    fun putSettingValue(key: String, value: String?) {
        val key_pref = stringPreferencesKey(key)
        CoroutineScope(Dispatchers.IO).launch {
            // Save the value somewhere
            dataStore.edit { preferences ->
                preferences[key_pref] = value ?: "none"
            }
        }
    }

    fun getSettingValue(key: String, defValue: String?): String? =
        runBlocking { dataStore.data.map { preferences -> preferences[stringPreferencesKey(key)] ?: defValue }.first() }
}