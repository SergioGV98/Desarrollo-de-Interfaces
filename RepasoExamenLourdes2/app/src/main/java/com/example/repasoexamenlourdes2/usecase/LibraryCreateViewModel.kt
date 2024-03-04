package com.example.repasoexamenlourdes2.usecase

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repasoexamenlourdes2.data.Book
import com.example.repasoexamenlourdes2.repository.LibraryRepositoryDB
import com.example.repasoexamenlourdes2.ui.LibraryCreateState

class LibraryCreateViewModel: ViewModel() {

    private var state = MutableLiveData<LibraryCreateState>()
    private var repositoryDB = LibraryRepositoryDB()
    var bookName = MutableLiveData<String>()

    fun validateBook(){
        when{
            TextUtils.isEmpty(bookName.value) -> state.value = LibraryCreateState.NameIsMandatory
            else -> {state.value = LibraryCreateState.OnSuccess}
        }
    }

    fun insertBook(book: Book){
        repositoryDB.insert(book)
    }

    fun lastId(): Long{
        return repositoryDB.selectLastId()
    }


    fun getState(): LiveData<LibraryCreateState>{
        return state
    }

}