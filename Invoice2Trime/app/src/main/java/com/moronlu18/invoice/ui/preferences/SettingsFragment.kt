package com.moronlu18.invoice.ui.preferences

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.moronlu18.invoice.Locator
import com.moronlu18.invoice.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //Obtener el DataStore que se quiere que utilice los componentes visuales de las Preferencias
        //Cuando se modifica el gestor de las preferencias se utiliza en todos los PreferenceFragment
        //Ya no se utiliza el fichero shared_preferences

        preferenceManager.preferenceDataStore = Locator.settingsPreferencesRepository

        val accountPreference = preferenceManager.findPreference<Preference>(getString(R.string.key_account))
        accountPreference?.setOnPreferenceClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_accountFragment2)
            true
        }
    }
}