package com.moronlu18.repository

import com.google.firebase.auth.FirebaseAuth
import com.moronlu18.accountsignin.data.network.Resource
import kotlinx.coroutines.Dispatchers

class AuthFirebaseRepository() {
    private var authFirebase = FirebaseAuth.getInstance()

    fun login(email: String, password: String): Resource {
        with(Dispatchers.IO){

        }

    }
}
