package com.moronlu18.accounts.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverters
import com.moronlu18.accounts.AccountId

@ProvidedTypeConverter

class AccountIdTypeConverter  {
    @TypeConverters
    fun toAccountId (value: Int): AccountId {
        return AccountId(value)
    }
    @TypeConverters
    fun fromAccountId(accountId: AccountId): Int{
        return accountId.value
    }
}