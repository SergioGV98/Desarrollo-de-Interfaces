package com.moronlu18.accounts.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverters
import com.moronlu18.accounts.entity.Email

@ProvidedTypeConverter
class EmailTypeConverter {
    @TypeConverters
    fun toEmail (value: String): Email = Email(value)

    @TypeConverters
    fun fromEmail(email: Email): String = email.value
}