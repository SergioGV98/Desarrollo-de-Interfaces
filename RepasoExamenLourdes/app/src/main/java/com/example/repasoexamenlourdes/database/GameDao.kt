package com.example.repasoexamenlourdes.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.repasoexamenlourdes.data.Juego
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM juegos")
    fun selectALL(): Flow<List<Juego>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(juego: Juego): Long

    @Delete
    fun delete(juego: Juego)

    @Query("SELECT id FROM juegos ORDER BY id DESC LIMIT 1")
    fun selectLastInsertedId(): Long?


}