package com.example.repasobd.repository

import com.example.repasobd.base.Book
import com.example.repasobd.database.InvoiceDatabase

class BookProviderDB {

    fun insert(book: Book){
        InvoiceDatabase.getInstance().bookDao().insert(book)
    }
}