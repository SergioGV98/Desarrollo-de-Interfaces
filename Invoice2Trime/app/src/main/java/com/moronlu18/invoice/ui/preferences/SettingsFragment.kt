package com.moronlu18.invoice.ui.preferences

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.moronlu18.invoice.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}