package com.moronlu18.invoice.ui.preferences

import android.os.Bundle
import android.text.InputType
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.moronlu18.invoice.R

class AccountFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.account_fragment, rootKey)

        initPreferenceEmail()
        initPreferencePassword()
    }

    private fun initPreferenceEmail() {
        val email = preferenceManager.findPreference<EditTextPreference>(getString(R.string.key_email))
        email?.setOnBindEditTextListener {
            it.setText("Informatica@iesportada.org")
            it.isEnabled=false
        }
    }

    private fun initPreferencePassword() {
        val password = preferenceManager.findPreference<EditTextPreference>(getString(R.string.key_password))
        password?.setOnBindEditTextListener {
            it.setText("Soy una contraseña")
            it.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            it.selectAll()
        }
    }

}