package com.moronlu18.accountsignin.ui

import com.moronlu18.accounts.Account

sealed class SignInState {

    object EmailEmptyError: SignInState()
    data object EmailFormatError: SignInState()
    data object PasswordEmptyError: SignInState()
    data object PasswordFormatError: SignInState()
    data class AuthencationError(var message: String): SignInState()
    data class Success(var account: Account): SignInState()

    //Se debe crear una clase que contiene un valor booleano que indica si se muestra el progress bar
    data class Loading(var value: Boolean):SignInState()


}