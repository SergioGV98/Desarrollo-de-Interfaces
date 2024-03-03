package com.example.repasobd.base

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book(
    @PrimaryKey
    val id: Int,
    var bookName: String,
    var bookCategory: CategoryBook
) {
    companion object{
        fun create(
            id: Int,
            bookName: String,
            bookCategory: CategoryBook
        ): Book {
            return Book(
                id = id,
                bookName = bookName,
                bookCategory = bookCategory
            )
        }
    }
}