package com.moronlu18.account.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moronlu18.data.account.User
import com.moronlu18.data.account.UserSignUp
import com.moronlu18.network.Resource
import com.moronlu18.repository.UserRepository
import com.moronlu18.repository.UserRepositoryv2
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {


    private var state = MutableLiveData<SignUpState>()
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password1 = MutableLiveData<String>()
    val password2 = MutableLiveData<String>()
    private val pattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

    fun validateSignUp() {
        viewModelScope.launch {


            UserRepositoryv2.selectAll()
            when {

                name.value.isNullOrBlank() -> state.value = SignUpState.NameEmptyError
                email.value.isNullOrBlank() -> state.value = SignUpState.EmailEmptyError
                !pattern.matcher(email.value!!).matches() -> state.value =
                    SignUpState.EmailFormatError

                password1.value.isNullOrBlank() -> state.value = SignUpState.PasswordEmptyError
                password2.value.isNullOrBlank() -> state.value = SignUpState.PasswordEmptyError
                !isEqualPassword(password1.value!!, password2.value!!) -> state.value =
                    SignUpState.PasswordNotEquals

                else -> {

                    state.value = SignUpState.Loading(true)
                    Log.i("viewModel", "He pasado por aquí")

                    val result = UserRepositoryv2.insert(User(name.value!!, email.value!!))

                    state.value = SignUpState.Loading(false)

                    when (result) {

                        is Resource.Success<*> -> {

                            state.value = SignUpState.OnSuccess(result.data as User)
                            //state.value = UserListState.Success(result.data as ArrayList<User>)



                            //Locator.userPreferencesRepository
                        }

                        is Resource.Error -> {
                            state.value = SignUpState.AuthencationError(result.exception.message!!)
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun isEqualPassword(pass1: String, pass2: String): Boolean {
        return pass1 == pass2
    }

    fun getState(): LiveData<SignUpState> {
        return state
    }



    /*

    fun addUserSignUp(user: UserSignUp) {
        UserRepository.addUser(user.toUser())
    }

    fun addUserDirect(user: User) {
        UserRepository.addUser(user)
    }*/

    fun addUserDataBase(user: User){
        UserRepositoryv2.insert(user)
    }
}