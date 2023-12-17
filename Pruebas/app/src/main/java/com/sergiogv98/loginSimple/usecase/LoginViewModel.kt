package com.sergiogv98.loginSimple.usecase

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergiogv98.loginSimple.ui.login.LoginState

class LoginViewModel : ViewModel() {

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    private var state = MutableLiveData<LoginState>()

    fun loginSucess() {
        when {
            TextUtils.isEmpty(email.value) -> state.value = LoginState.EmailIsEmpty
            TextUtils.isEmpty(password.value) -> state.value = LoginState.PasswordIsEmpty
            else -> {
                state.value = LoginState.OnSucess
            }
        }
    }


    fun getState(): LiveData<LoginState> {
        return state
    }

}