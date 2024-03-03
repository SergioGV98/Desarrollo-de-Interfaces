package com.example.repasoexamenlourdes.usecase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.repasoexamenlourdes.data.Juego
import com.example.repasoexamenlourdes.repository.GameRepositoryDB

class GameListViewModel: ViewModel() {

    private var repositoryDB = GameRepositoryDB()
    var allGame = repositoryDB.getGamesList().asLiveData()


    fun deleteGame(juego: Juego){
        repositoryDB.delete(juego)
    }
}