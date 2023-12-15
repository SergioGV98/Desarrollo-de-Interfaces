package com.moronlu18.accounts.repository

import com.moronlu18.accounts.entity.UserSignUp

class RegisterRepository private constructor() {

    companion object {
        var dataSet: MutableList<UserSignUp> = mutableListOf()

        init {
            RegisterRepository.initDataSetUser()
        }

        private fun initDataSetUser() {
        }
    }
}