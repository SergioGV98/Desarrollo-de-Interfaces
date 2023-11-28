package com.moronlu18.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.moronlu18.accounts.entity.account.Account
import com.moronlu18.accounts.entity.account.AccountException
import com.moronlu18.accounts.entity.account.AccountState
import com.moronlu18.accountsignin.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await

class AuthFirebaseRepository() {
    private var authFirebase = FirebaseAuth.getInstance()
    suspend fun login(email: String, password: String): Resource {
        with(Dispatchers.IO){
            try{
                val authResult: AuthResult = authFirebase.signInWithEmailAndPassword(email,password).await()
                val user =  authResult.user
                Account.create(id = user.hashCode(), email = email, password = password, displayName = user?.displayName, state = AccountState.VERIFIED)
                return Resource.Success(user)
            } catch(e: Exception){
                Resource.Error(e)
            }
        }
        return Resource.Error(AccountException.InvalidEmailFormat())
    }
}