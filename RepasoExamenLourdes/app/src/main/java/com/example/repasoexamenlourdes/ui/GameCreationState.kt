package com.example.repasoexamenlourdes.ui

sealed class GameCreationState {

    data object NameIsMandatory: GameCreationState()
    data object OnSuccess: GameCreationState()

}