package com.moronlu18.account.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moronlu18.accountsignin.R

class SignInVersion2 : Fragment() {

    /**
     * Este SignIn es un modelo que hice en su momento.
     * Se usará en un futuro.
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signinv2, container, false)
        //return inflater.inflate(R.layout.fragment_signinv2, container, false)
    }
}