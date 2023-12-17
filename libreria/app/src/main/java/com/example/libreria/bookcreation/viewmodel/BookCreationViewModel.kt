package com.example.libreria.bookcreation.viewmodel

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.libreria.bookcreation.BookCreationState
import com.example.libreria.data.Book
import com.example.libreria.repository.BookRepository

class BookCreationViewModel : ViewModel() {

    var bookName = MutableLiveData<String>()
    var bookPrice = MutableLiveData<String>()
    private var state = MutableLiveData<BookCreationState>()

    fun validateCreationBook(){
        when{
            TextUtils.isEmpty(bookName.value) -> state.value = BookCreationState.BookNameIsMandatory
            TextUtils.isEmpty(bookPrice.value) -> state.value = BookCreationState.BookPriceIsMandatory
            validatePrice() -> state.value = BookCreationState.BookPriceNotValid
            else -> {
                state.value = BookCreationState.OnSuccess
            }

        }
    }

    fun addBook(book: Book){
        BookRepository.bookRepository.add(book)
    }

    private fun validatePrice(): Boolean{
        if (bookPrice.value!!.toDouble() <= 0){
            return true
        }
        return false
    }

    fun idBook(): Int{
        return BookRepository.bookRepository.size+1
    }

    fun getState(): LiveData<BookCreationState>{
        return state
    }
}