package com.moronlu18.accounts.database.dao

import androidx.room.Dao
import androidx.room.ForeignKey.Companion.RESTRICT
import androidx.room.Insert
import androidx.room.Query
import com.moronlu18.accounts.entity.User

@Dao
interface UserDao {
    @Insert (onConflict =  RESTRICT)
    fun insert(user: User): Long

    @Query ("SELECT * FROM user")
    fun selectAll(): List<User>
}