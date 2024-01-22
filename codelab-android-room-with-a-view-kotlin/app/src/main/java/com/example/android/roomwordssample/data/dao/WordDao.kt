package com.example.android.roomwordssample.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.android.roomwordssample.data.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word)

    @Update
    fun update (word: Word)

    @Delete
    fun delete(word: Word)

    @Query ("DELETE FROM word")
    fun deleteAll ()

    @Query ("SELECT * FROM word ORDER BY word ASC")
    fun orderBy(): Flow<List<Word>>

}