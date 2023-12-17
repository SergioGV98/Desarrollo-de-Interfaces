package com.example.libreria.booklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.libreria.R
import com.example.libreria.repository.BookRepository

class BookAdapter: RecyclerView.Adapter<ListBookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListBookViewHolder(layoutInflater.inflate(R.layout.item_book, parent, false))
    }

    override fun getItemCount(): Int {
        return BookRepository.bookRepository.size
    }

    override fun onBindViewHolder(holder: ListBookViewHolder, position: Int) {
        val item = BookRepository.bookRepository[position]
        holder.render(item)
    }
}