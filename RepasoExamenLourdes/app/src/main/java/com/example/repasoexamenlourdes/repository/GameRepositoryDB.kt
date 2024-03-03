package com.example.repasoexamenlourdes.repository

import com.example.repasoexamenlourdes.data.Juego
import com.example.repasoexamenlourdes.database.GameDatabase
import kotlinx.coroutines.flow.Flow

class GameRepositoryDB {
    fun insert(juego: Juego){
        GameDatabase.getInstance().gameDao().insert(juego)
    }

    fun getGamesList(): Flow<List<Juego>> {
        return GameDatabase.getInstance().gameDao().selectALL()
    }

    fun getNextGameId(): Long {
        val lastInsertedId = GameDatabase.getInstance().gameDao().selectLastInsertedId() ?: 0
        return lastInsertedId + 1
    }

    fun delete(juego: Juego){
        return GameDatabase.getInstance().gameDao().delete(juego)
    }


}