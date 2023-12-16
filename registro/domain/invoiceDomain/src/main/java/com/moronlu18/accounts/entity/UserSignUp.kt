package com.moronlu18.accounts.entity

import com.moronlu18.accounts.TypeAccounts
import com.moronlu18.accounts.TypeProfile

data class UserSignUp(
    override val email: String,
    val password: String,
    val confirmPassword: String,
    val typeAccount: TypeAccounts,
    val typeProfile: TypeProfile
) : User()