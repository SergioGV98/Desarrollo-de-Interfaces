package com.moronlu18.accounts.database.dao

import androidx.room.Dao
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.Query
import com.moronlu18.accounts.entity.BusinessProfile

@Dao
interface BusinessProfileDao {
    @Insert(onConflict = ForeignKey.RESTRICT)
    suspend fun insert (businessProfile: BusinessProfile): Long

    @Query("SELECT * FROM businessprofile WHERE id=:businessProfileID")
    suspend fun selectBusinessProfile (businessProfileID: Int) : BusinessProfile
}