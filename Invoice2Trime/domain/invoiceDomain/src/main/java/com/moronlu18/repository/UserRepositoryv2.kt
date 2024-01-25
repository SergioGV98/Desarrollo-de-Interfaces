package com.moronlu18.repository

import com.moronlu18.data.account.User
import com.moronlu18.database.InvoiceDatabase
import kotlinx.coroutines.flow.Flow

class UserRepositoryv2 {

    companion object{

        fun insert(user:User){
            InvoiceDatabase.getInstance()?.userDao()?.insert(user)
        }

        fun selectAll(){
            InvoiceDatabase.getInstance()?.userDao()?.selectAll()
        }

        fun getUserList(): Flow<List<User>>? {
            return InvoiceDatabase.getInstance()?.userDao()?.selectAll()
        }
    }

}

