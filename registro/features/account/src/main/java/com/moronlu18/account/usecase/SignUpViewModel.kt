package com.moronlu18.account.usecase

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moronlu18.accounts.repository.UserRepository
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    var name = MutableLiveData<String>()

    private var state = MutableLiveData<SignUpState>()

    fun validateRegister() {
        when {
            TextUtils.isEmpty(name.value) -> state.value = SignUpState.NameIsEmpty
            else -> {
                viewModelScope.launch {
                    state.value = SignUpState.Success
                    //UserRepository.dataSet.add() //Al repositorio
                }
            }
        }
    }

    fun getState(): LiveData<SignUpState> {
        return state
    }

}