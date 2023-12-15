package com.moronlu18.account.usecase

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moronlu18.accounts.repository.UserRepository

class SignUpViewModel : ViewModel() {

    var name = MutableLiveData<String>()

    private var state = MutableLiveData<SignUpState>()

    fun validateRegister() {
        when {
            TextUtils.isEmpty(name.value) -> state.value = SignUpState.NameIsEmpty
            else -> {
                //UserRepository.dataSet.add() //Al repositorio
                state.value = SignUpState.Success
            }
        }
    }

    fun getState(): LiveData<SignUpState> {
        return state
    }

}