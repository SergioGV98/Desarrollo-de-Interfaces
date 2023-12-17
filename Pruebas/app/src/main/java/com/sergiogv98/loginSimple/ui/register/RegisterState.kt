package com.sergiogv98.loginSimple.ui.register

sealed class RegisterState {
    data object NameIsMandatory : RegisterState()
    data object EmailIsMandatory : RegisterState()
    data object EmailIsNotUnique : RegisterState()
    data object PasswordIsMandatory : RegisterState()
    data object PasswordRepeatIsMandatory : RegisterState()
    data object PasswordMustMatch : RegisterState()
    data object OnSuccess : RegisterState()

}