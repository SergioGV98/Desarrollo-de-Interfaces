package com.example.repasobd.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.repasobd.base.Book
import com.example.repasobd.databinding.ItemBookBinding

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemBookBinding.bind(itemView)

    fun bind(book: Book){
        with(binding){
            itemBookId.text = book.id.toString()
            itemBookName.text = book.bookName
            itemBookCategory.text = book.bookCategory.toString()
        }
    }
}