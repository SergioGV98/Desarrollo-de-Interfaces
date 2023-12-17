package com.example.libreria.booklist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.libreria.data.Book
import com.example.libreria.databinding.ItemBookBinding

class ListBookViewHolder(inflate: View) : RecyclerView.ViewHolder(inflate) {

    val binding = ItemBookBinding.bind(inflate)

    fun render(book: Book){
        binding.txtNameBook.text = book.name
        binding.txtAuthorBook.text = book.author
    }
}