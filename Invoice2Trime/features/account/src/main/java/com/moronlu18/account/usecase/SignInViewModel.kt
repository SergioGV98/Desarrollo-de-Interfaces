package com.moronlu18.account.usecase

//import android.accounts.Account
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moronlu18.accounts.database.repository.UserRepository
import com.moronlu18.accounts.entity.Account
import com.moronlu18.accounts.entity.User
import com.moronlu18.accounts.network.Resource
import com.moronlu18.firebase.AuthFirebase
import com.moronlu18.invoice.Locator
//import com.moronlu18.accounts.entity.Account
import kotlinx.coroutines.launch

const val TAG = "ViewModel"

class SignInViewModel : ViewModel() {
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    val authFirebase = AuthFirebase()
    val userRepository = UserRepository()


    private var state = MutableLiveData<SignInState>()


    /**
     * Esta función se ejecuta directamente desde el fichero xml al user
     *  DataBinding android:onClick="@{()->viewmodel.validateCredentials()}"
     */

    fun validateCredentials() {
        Log.i(TAG, "El email es: ${email.value} y el password es ${password.value}")

        when {
            TextUtils.isEmpty(email.value) -> state.value = SignInState.EmailEmptyError
            TextUtils.isEmpty(password.value) -> state.value = SignInState.PasswordEmptyError

            else -> {
                /*Se crea una corrutina que suspende el hilo principal hasta que el
                bloque with Context el repositorio termina de ejecutarse.
                */

                viewModelScope.launch {

                    state.value = SignInState.Loading(true)

                    val result = AuthFirebase().login(email.value!!, password.value!!)

                    state.value = SignInState.Loading(false)

                    when (result) {
                        //esto es una clase sellada (Resource)
                        is Resource.Success<*> -> {

                            //state.value = SignInState.Success(result)

                            //Este es el que utiliza
                            //state.value = SignInState.Success(result.data as? Account)

                            val account = result.data as Account


                            state.value = SignInState.Success(account)

                            //guardar la información del usuario en el almacén de datos user_preferences
                            Locator.userPreferencesRepository.saveUser(
                                account.email.value,
                                account.password.toString(),
                                account.id
                            )
                            userRepository.insert(User("Sergio", account.email))
                        }

                        is Resource.Error -> {
                            Log.i(TAG, "Información del dato ${result.exception.message}")

                            //De mientras está esto pausado.
                            state.value = SignInState.AuthencationError(result.exception.message!!)
                        }
                    }
                }
            }
        }

    }

    /**
     * Se crea solo la función de obtención de la variable State.
     * No se puede modificar su valor fuera de ViewModel.
     */
    fun getState(): LiveData<SignInState> {
        return state
    }


}