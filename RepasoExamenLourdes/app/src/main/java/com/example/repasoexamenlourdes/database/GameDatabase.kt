package com.example.repasoexamenlourdes.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.repasoexamenlourdes.Locator
import com.example.repasoexamenlourdes.converter.GameGenreConverter
import com.example.repasoexamenlourdes.data.Genero
import com.example.repasoexamenlourdes.data.Juego
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


@Database(
    entities = [Juego::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(
    GameGenreConverter::class
)

abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getInstance(): GameDatabase {
            return INSTANCE ?: synchronized(GameDatabase::class) {
                val instance = buildDatabase()
                INSTANCE = instance
                instance
            }
        }

        private fun buildDatabase(): GameDatabase {
            return Room.databaseBuilder(
                Locator.requireApplication, GameDatabase::class.java, "Game"
            ).fallbackToDestructiveMigration().allowMainThreadQueries()
                .addTypeConverter(GameGenreConverter())
                .build()
        }
    }

}