package com.moronlu18.accounts.database.repository

import com.moronlu18.accounts.database.InvoiceDatabase
import com.moronlu18.accounts.entity.User

class UserRepository {
    fun insert(user:User){
        InvoiceDatabase.getInstance()?.userDao()?.insert(user)
    }
}