package com.moronlu18.account.ui.signup

import com.moronlu18.accounts.network.Resource

sealed class SignUpState {

    data object NameIsEmpty : SignUpState()
    data object EmailIsEmpty : SignUpState()
    data object EmailCannotFind : SignUpState()
    data class RegisterError(var message: String) : SignUpState()
    data object Success : SignUpState()
}