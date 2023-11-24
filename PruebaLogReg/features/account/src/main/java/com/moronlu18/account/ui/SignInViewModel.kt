package com.moronlu18.account.ui

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moronlu18.repository.UserRepository
import com.moronlu18.accountsignin.data.network.Resource
import kotlinx.coroutines.launch

const val TAG = "ViewModel"

class SignInViewModel : ViewModel() {

    //LiveData que controlan los datos introducidos en la IU
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    //LiveData que tendra su Observador en el Fragment y controla las excepciones/casos de uso
    //de la operacion Login

    private var state = MutableLiveData<SignInState>()

    //Crear la clase sellada que permitira gestionar las excepciones de la vista

    /**
     * Esta funcion se ejecuta directamente desde el fichero XML al usar
     * DataBinding
     * android:onClick="@{()->viewmodel.validateCredentials()}"
     */
    fun validateCredentials() {
        //Log.i(TAG, "El email es $email y el password es $password")
        when {
            TextUtils.isEmpty(email.value) -> state.value = SignInState.EmailEmptyError
            TextUtils.isEmpty(password.value) -> state.value = SignInState.PasswordEmptyError
            //EmailFormat
            //PasswordFormat
            else -> {
                //Se crea una corrutina que suspende el hilo principal hasta que el
                //bloque withContext del repositorio termina de ejecutarse.
                viewModelScope.launch {
                  //Vamos a ejecturar el Login del repositorio - > que pregunta a la capa de la infraestructura
                    //is cuando sea un data class
                  when(val result = UserRepository.login(email.value!!, password.value!!)){
                    is Resource.Success<*> -> {
                      //Aqui tenemos que hacer un Casting Seguro porque el tipo de dato es generico T
                    }
                    is Resource.Error -> {
                      Log.i(TAG, "Informacion del error ${result.exception.message}")
                      state.value = SignInState.AuthencationError(result.exception.message!!)
                    }
                  }
                }
            }
        }

    }

    /**
     * Se crea solo la funcion de obtencion de la variable State. No se puede modificar su
     * valor fuera de ViewModel.
     */
    fun getState(): LiveData<SignInState> {
        return state
    }

}