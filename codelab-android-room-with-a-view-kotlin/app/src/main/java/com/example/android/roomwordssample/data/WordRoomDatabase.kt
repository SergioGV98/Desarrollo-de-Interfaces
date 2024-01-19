package com.example.android.roomwordssample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android.roomwordssample.data.dao.WordDao
import com.example.android.roomwordssample.data.model.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
    @Database(entities = [Word::class], version = 1, exportSchema = false)

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var wordDao = database.wordDao()

                    var word = Word(1,"Mundo","Es la descripcioń de la palabra")
                    wordDao.insert(word)
                    word = Word(2,"Cruel","Es la descripcioń de la palabra")
                    wordDao.insert(word)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}