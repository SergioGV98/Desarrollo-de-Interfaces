package com.moronlu18.accountsignin.ui

sealed class SignInState {

    object EmailEmptyError: SignInState()
    data object EmailFormatError: SignInState()
    data object PasswordEmptyError: SignInState()
    data object PasswordFormatError: SignInState()
    data object Success: SignInState()


}