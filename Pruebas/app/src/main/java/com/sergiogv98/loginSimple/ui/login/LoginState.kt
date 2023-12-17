package com.sergiogv98.loginSimple.ui.login

sealed class LoginState  {

    data object EmailIsEmpty : LoginState()
    data object PasswordIsEmpty: LoginState()
    data object OnSucess: LoginState()
    //Crear caso de fallo y de exito

}