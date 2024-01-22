package com.moronlu18.accounts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.moronlu18.accounts.converter.AccountIdTypeConverter
import com.moronlu18.accounts.converter.EmailTypeConverter
import com.moronlu18.accounts.entity.Account
import com.moronlu18.accounts.entity.BusinessProfile
import com.moronlu18.accounts.entity.User
import com.moronlu18.accounts.database.dao.AccountDao
import com.moronlu18.accounts.database.dao.BusinessProfileDao
import com.moronlu18.accounts.database.dao.UserDao
import com.moronlu18.invoice.Locator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database (entities = [Account::class, BusinessProfile::class, User::class], version = 1, exportSchema = false)
@TypeConverters(AccountIdTypeConverter::class, EmailTypeConverter::class)
abstract class InvoiceDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun accountDao(): AccountDao
    abstract fun businessProfileDao(): BusinessProfileDao

    companion object {
        @Volatile
        private var instance: InvoiceDatabase? = null
        fun getInstance(): InvoiceDatabase? {
            if (instance == null) {
                synchronized(InvoiceDatabase::class) {
                    instance = buildDatabase()
                }
            }
            return instance
        }

        private fun buildDatabase(): InvoiceDatabase {
            return Room.databaseBuilder(
                Locator.requireApplication,
                InvoiceDatabase::class.java,
                "Invoice"
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .addTypeConverter(AccountIdTypeConverter())
                .addTypeConverter(EmailTypeConverter())
                .addCallback(
                    RoomDbInitializer(instance)
                ).build()
        }
    }

    class RoomDbInitializer(val instance: InvoiceDatabase?) : RoomDatabase.Callback() {

        private val applicationScope = CoroutineScope(SupervisorJob())

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            applicationScope.launch(Dispatchers.IO) {
                populateDatabase()
            }
        }

        private suspend fun populateDatabase() {
            populateUsers()
        }

        private suspend fun populateUsers() {
        }
    }
}