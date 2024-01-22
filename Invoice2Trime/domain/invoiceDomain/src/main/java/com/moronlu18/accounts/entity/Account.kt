package com.moronlu18.accounts.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.ForeignKey.Companion.RESTRICT
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.moronlu18.accounts.converter.AccountIdTypeConverter
import com.moronlu18.accounts.converter.EmailTypeConverter
import com.moronlu18.accounts.enum_entity.AccountState

@Entity(tableName = "account", foreignKeys = [ForeignKey(
    entity = BusinessProfile::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("businessProfile"),
    onDelete = RESTRICT,
    onUpdate = CASCADE
)])
class Account (
    @PrimaryKey
    @TypeConverters(AccountIdTypeConverter::class)
    val id: Int,
    @TypeConverters(EmailTypeConverter::class)
    val email: Email,
    val password: String?,
    val displayName: String?,
    var state: AccountState = AccountState.UNVERIFIED,
    var businessProfile: Int?,
) {

    /**
     * Al utilizar un objeto acompañante con una función y el constructor privado de la clase
     * garantizo el modo/restricciones que  tengo al crear un objeto de la clase.
     */
    //Se hace para tener control de la propiedad sobre ellos.
    //Y así garantizo que todas las cuenta sean no verificados.
    companion object {
        fun create(id: Int, email: Email, password: String?, displayName: String?, state: AccountState, businessProfile: Int?): Account {
            return Account(
                id = id,
                email = email,
                password=password,
                displayName = displayName,
                state= AccountState.UNVERIFIED, //state=state,
                businessProfile = businessProfile,
            )
        }

    }

}