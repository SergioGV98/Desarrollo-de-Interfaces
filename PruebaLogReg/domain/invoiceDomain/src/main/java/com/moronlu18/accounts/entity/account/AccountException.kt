package com.moronlu18.accounts.entity.account

sealed class AccountException (message:String="") : RuntimeException(message) {
    class InvalidEmailFormat: AccountException("Email con formato invalido")
}