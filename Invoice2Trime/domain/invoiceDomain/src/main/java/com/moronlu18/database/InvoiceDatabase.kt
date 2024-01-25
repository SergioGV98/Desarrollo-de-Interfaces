package com.moronlu18.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.moronlu18.data.account.Account
import com.moronlu18.data.account.BusinessProfile
import com.moronlu18.data.account.User
import com.moronlu18.data.converter.AccountIdTypeConverter
import com.moronlu18.data.converter.EmailTypeConverter
import com.moronlu18.database.dao.AccountDao
import com.moronlu18.database.dao.BusinessProfileDao
import com.moronlu18.database.dao.UserDao
import com.moronlu18.invoice.Locator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

import kotlinx.coroutines.launch


@Database(
    entities = [Account::class, BusinessProfile::class, User::class],
    version = 1,
    exportSchema = false
)
//Hay que decir que convertidores vamos a utilizar
//El primero se lo paso por el parametro.
//El convertidores de tupo que vamos a utilizar
@TypeConverters(AccountIdTypeConverter::class, EmailTypeConverter::class)
abstract class InvoiceDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun accountDao(): AccountDao
    abstract fun businessProfileDao(): BusinessProfileDao

    companion object {
        @Volatile
        private var INSTANCE: InvoiceDatabase? = null

        //Nosotros no propagamos desde el getInstance
        fun getInstance(): InvoiceDatabase? {
            return INSTANCE ?: synchronized(InvoiceDatabase::class) {
                val instance = buildDatabase()
                INSTANCE = instance
                instance
            }
        }

        //El contexto
        private fun buildDatabase(): InvoiceDatabase {
            return Room.databaseBuilder(
                Locator.requireApplication,
                InvoiceDatabase::class.java,
                "Invoice"
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                //y creamos un objeto de esta clase para convertir de un tipo a otro.
                .addTypeConverter(AccountIdTypeConverter())
                .addTypeConverter(EmailTypeConverter())
                .addCallback(
                    RoomDbInitializer(INSTANCE)
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
            instance.let { invoiceDatabase ->
                invoiceDatabase?.userDao()?.insert(User("Alejandro", "abc@hotmail.es"))
                invoiceDatabase?.userDao()?.insert(User("Koba", "cbd@hotmail.es"))
                invoiceDatabase?.userDao()?.insert(User("Zanahoria", "zanah@hotmail.es"))
                invoiceDatabase?.userDao()?.insert(User("Rim", "rim@hotmail.es"))
                invoiceDatabase?.userDao()?.insert(User("Mallorca", "123cab@hotmail.es"))
                invoiceDatabase?.userDao()?.insert(User("Paella", "paella@hotmail.com"))
                invoiceDatabase?.userDao()?.insert(User("Cebolla", "op@hotmail.es"))
                invoiceDatabase?.userDao()?.insert(User("Rabano", "mesa@gmail.com"))
            }
        }
    }
}