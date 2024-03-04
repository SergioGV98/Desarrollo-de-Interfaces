package com.example.repasoexamenlourdes2.usecase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.repasoexamenlourdes2.repository.LibraryRepositoryDB

class LibraryListViewModel: ViewModel() {

    private var repositoryDB = LibraryRepositoryDB()
    var allBooks = repositoryDB.selectAllBook().asLiveData()

}