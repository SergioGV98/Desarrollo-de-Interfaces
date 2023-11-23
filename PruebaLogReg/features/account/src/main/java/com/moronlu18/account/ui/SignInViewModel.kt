package com.moronlu18.account.ui

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val TAG="ViewModel"

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
  fun validateCredentials(){
    //Log.i(TAG, "El email es $email y el password es $password")
    when{
      TextUtils.isEmpty(email.value) -> state.value = SignInState.EmailEmptyError
      TextUtils.isEmpty(password.value) -> state.value = SignInState.PasswordEmptyError
      //else -> state.value = SignInState.Success
    }

  }

  /**
   * Se crea solo la funcion de obtencion de la variable State. No se puede modificar su
   * valor fuera de ViewModel.
   */
  fun getState(): LiveData<SignInState>{
    return state
  }

}