package com.moronlu18.invoice.data.userpreferences

import android.R
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Esta clase contiene todos los metodos necesarios para leer y guardar datos del usuario,
 * preferencias en el almacen de datos "user_preferences"
 */

class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {
    companion object UserStoreKeys {
        private val EMAIL = stringPreferencesKey("email")
        private val PASSWORD = stringPreferencesKey("password")
    }

    /**
     * Metodo que guarda la informacion del usuario de Firebase en user_preference
     */
    suspend fun saveUser(email: String, password: String) {
        dataStore.edit {
            preferences ->
            preferences[EMAIL] = email
            preferences[PASSWORD] = password
        }
    }
}