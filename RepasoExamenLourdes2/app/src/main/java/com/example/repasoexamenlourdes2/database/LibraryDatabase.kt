package com.example.repasoexamenlourdes2.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.repasoexamenlourdes2.Locator
import com.example.repasoexamenlourdes2.data.Book


@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)

/*@TypeConverters(
    GameGenreConverter::class
)*/

abstract class LibraryDatabase : RoomDatabase() {
    abstract fun libraryDao(): LibraryDao

    companion object {
        @Volatile
        private var INSTANCE: LibraryDatabase? = null

        fun getInstance(): LibraryDatabase {
            return INSTANCE ?: synchronized(LibraryDatabase::class) {
                val instance = buildDatabase()
                INSTANCE = instance
                instance
            }
        }

        private fun buildDatabase(): LibraryDatabase {
            return Room.databaseBuilder(
                Locator.requestApplication, LibraryDatabase::class.java, "library"
            ).fallbackToDestructiveMigration().allowMainThreadQueries()
                //.addTypeConverter(GameGenreConverter())
                .build()
        }
    }

}