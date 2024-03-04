package com.example.repasoexamenlourdes2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Book")
data class Book(
    @PrimaryKey
    var id: Int,
    var name: String,
    var genre: Genre) {
}