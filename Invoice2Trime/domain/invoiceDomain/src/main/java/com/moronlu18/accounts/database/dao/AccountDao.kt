package com.moronlu18.accounts.database.dao

import androidx.room.Dao
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.Query
import com.moronlu18.accounts.entity.Account
import com.moronlu18.accounts.entity.BusinessProfile

@Dao
interface AccountDao {
    @Insert(onConflict = ForeignKey.RESTRICT)
    suspend fun insert(account: Account): Long

    @Insert
    suspend fun update(account: Account)

    @Query("SELECT * FROM account")
    suspend fun selectAll(): List<Account>

    //Se añade una query personalizada en un metodo que devuelve un objeto Account y el objeto
    //BusinessProfile
    @Query("SELECT * FROM account JOIN businessprofile ON account.businessProfile = businessProfile.id")
    suspend fun loadAccountAndBusinessProfile(): Map<Account, BusinessProfile>
}