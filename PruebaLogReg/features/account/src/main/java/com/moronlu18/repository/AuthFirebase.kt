package com.moronlu18.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.moronlu18.accounts.entity.account.Account
import com.moronlu18.accounts.entity.account.Email
import com.moronlu18.accountsignin.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthFirebase() {
    private var authFirebase = FirebaseAuth.getInstance()
    suspend fun login(email: String, password: String): Resource {
        return withContext(Dispatchers.IO){
            try{
                val authResult: AuthResult = authFirebase.signInWithEmailAndPassword(email,password).await()
                val account = Account.create(authResult.user.hashCode(), Email(email), password, authResult.user?.displayName)
                Resource.Success(account)
            } catch(e: Exception){
                Resource.Error(e)
            }
        }

    }
}