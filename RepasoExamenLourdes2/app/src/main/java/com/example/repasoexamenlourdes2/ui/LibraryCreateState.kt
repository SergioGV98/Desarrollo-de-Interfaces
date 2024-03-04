package com.example.repasoexamenlourdes2.ui

sealed class LibraryCreateState {

    data object NameIsMandatory: LibraryCreateState()
    data object OnSuccess: LibraryCreateState()

}