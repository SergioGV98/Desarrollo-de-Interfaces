package com.example.libreria.repository

import com.example.libreria.data.Book
import com.example.libreria.enums.CategoryBook

class BookRepository {

    companion object{

        var bookRepository = mutableListOf<Book>()

        init {

            initData()

        }

        private fun initData(){
            bookRepository.add(Book(1, "Nacidos de la bruma: El imperio final", "Brandom Sanderson", CategoryBook.FANTASIA, CategoryBook.FICCION, 29.95))
        }

        fun getRepository(): MutableList<Book> {
            return this.bookRepository
        }


    }


}