package com.example.repasoexamenlourdes2.repository

import com.example.repasoexamenlourdes2.data.Book
import com.example.repasoexamenlourdes2.database.LibraryDatabase
import kotlinx.coroutines.flow.Flow

class LibraryRepositoryDB {

    fun insert(book: Book){
        LibraryDatabase.getInstance().libraryDao().insert(book)
    }

    fun selectAllBook(): Flow<List<Book>> {
        return LibraryDatabase.getInstance().libraryDao().selectAll()
    }

    fun selectLastId(): Long {
        return LibraryDatabase.getInstance().libraryDao().selectLastId()
    }

}