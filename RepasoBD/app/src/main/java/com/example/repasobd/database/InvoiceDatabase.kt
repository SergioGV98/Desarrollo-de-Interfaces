package com.example.repasobd.database

import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.repasobd.base.Book
import com.example.repasobd.base.CategoryBook
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    //AccountIdTypeConverter::class,
)
abstract class InvoiceDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: InvoiceDatabase? = null

        fun getInstance(): InvoiceDatabase {
            return INSTANCE ?: synchronized(InvoiceDatabase::class) {
                val instance = buildDatabase()
                INSTANCE = instance
                instance
            }
        }

        private fun buildDatabase(): InvoiceDatabase {
            return Room.databaseBuilder(
                Locator.requireApplication, InvoiceDatabase::class.java, "library"
            ).fallbackToDestructiveMigration().allowMainThreadQueries()
                //.addTypeConverter(PhotoTypeConverter())
                .addCallback(
                    RoomDbInitializer(INSTANCE)
                ).build()
        }
    }

    class RoomDbInitializer(private val instance: InvoiceDatabase?) : Callback() {

        private val applicationScope = CoroutineScope(SupervisorJob())

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            applicationScope.launch(Dispatchers.IO) {
                populateDatabase()

            }
        }

        private suspend fun populateDatabase() {
            Log.d("Pruebas", "HI")
            populateBooks()
        }


        private fun populateBooks() {
            var customId = 1;

            getInstance().let {
                it.bookDao().insert(
                    Book(
                        customId++,
                        "Mistborn",
                        CategoryBook.FANTASIA
                    )
                )
            }
        }
    }
}