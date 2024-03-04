package com.example.repasoexamenlourdes2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.repasoexamenlourdes2.data.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface LibraryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book): Long

    @Query("SELECT * FROM Book")
    fun selectAll(): Flow<List<Book>>

    @Query("SELECT id FROM book ORDER BY id DESC LIMIT 1")
    fun selectLastId(): Long

}