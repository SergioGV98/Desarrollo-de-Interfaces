package com.sergiogv98.loginSimple.usecase

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergiogv98.loginSimple.data.Usuario
import com.sergiogv98.loginSimple.enums.TypeUser
import com.sergiogv98.loginSimple.repository.UserRepository
import com.sergiogv98.loginSimple.ui.register.RegisterState

class RegisterViewModel : ViewModel() {

    var nameUser = MutableLiveData<String>()
    var emailUser = MutableLiveData<String>()
    var passwordUser = MutableLiveData<String>()
    var passwordRepeatUser = MutableLiveData<String>()
    var spinnerPosition = MutableLiveData<Int>()
    private var state = MutableLiveData<RegisterState>()

    fun validateRegister() {
        when {
            TextUtils.isEmpty(nameUser.value) -> state.value = RegisterState.NameIsMandatory
            TextUtils.isEmpty(emailUser.value) -> state.value = RegisterState.EmailIsMandatory
            emailNotUnique() -> state.value = RegisterState.EmailIsNotUnique
            TextUtils.isEmpty(passwordUser.value) -> state.value = RegisterState.PasswordIsMandatory
            TextUtils.isEmpty(passwordRepeatUser.value) -> state.value =  RegisterState.PasswordRepeatIsMandatory
            samePasswords() -> state.value = RegisterState.PasswordMustMatch
            else -> {
                UserRepository.dataSet.add(Usuario(UserRepository.dataSet.size+1, nameUser.value!!,
                    emailUser.value!!, passwordUser.value!!, assignTypeUser()))
                state.value = RegisterState.OnSuccess
            }
        }
    }

    private fun samePasswords(): Boolean {
        return passwordUser.value != passwordRepeatUser.value
    }

    private fun assignTypeUser(): TypeUser{
        return when(spinnerPosition.value){
            0 -> TypeUser.USUARIO
            1 -> TypeUser.ADMINISTRADOR
            2 -> TypeUser.INVITADO
            else -> {TypeUser.USUARIO}
        }
    }

    private fun emailNotUnique(): Boolean {
        for (user in UserRepository.dataSet){
            if(user.email == emailUser.value){
                return true
            }
        }
        return false
    }


    fun getState(): LiveData<RegisterState> {
        return state
    }

}