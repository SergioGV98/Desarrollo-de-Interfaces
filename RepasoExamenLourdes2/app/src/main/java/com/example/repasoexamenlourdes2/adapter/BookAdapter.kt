package com.example.repasoexamenlourdes2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.repasoexamenlourdes2.R
import com.example.repasoexamenlourdes2.data.Book
import com.example.repasoexamenlourdes2.databinding.ItemBookBinding

class BookAdapter: ListAdapter<Book, BookAdapter.BookViewHolder>(BOOK_COMPARATOR) {

    inner class BookViewHolder(itemView: View) : ViewHolder(itemView){

        private val binding = ItemBookBinding.bind(itemView)

        fun render(book: Book){
            with(binding){
                binding.txtBookId.text = book.id.toString()
                binding.txtBookName.text = book.name
                binding.txtBookCategory.text = book.genre.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BookViewHolder(layoutInflater.inflate(R.layout.item_book, parent, false))
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = getItem(position)
        holder.render(item)
    }

    companion object{
        private val BOOK_COMPARATOR = object : DiffUtil.ItemCallback<Book>(){
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}