package com.example.libreria.data

import com.example.libreria.enums.CategoryBook

data class Book (val id: Int, val name: String, val author: String, val mainCategory: CategoryBook, val secondaryCategory: CategoryBook, val price: Double)