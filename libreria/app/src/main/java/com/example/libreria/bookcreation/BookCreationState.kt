package com.example.libreria.bookcreation

sealed class BookCreationState {

    data object BookNameIsMandatory : BookCreationState()
    data object BookPriceIsMandatory : BookCreationState()
    data object BookPriceNotValid : BookCreationState()
    data object OnSuccess : BookCreationState()

}