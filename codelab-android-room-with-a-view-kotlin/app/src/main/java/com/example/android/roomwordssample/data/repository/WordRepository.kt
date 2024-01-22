package com.example.android.roomwordssample.data.repository

import androidx.annotation.WorkerThread
import com.example.android.roomwordssample.data.dao.WordDao
import com.example.android.roomwordssample.data.model.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(val wordDao: WordDao) {

    // Room ejecuta todas las consultas en un hilo separado.
    // El flujo observado notificara al observador cuando los datos hayan cambiado.
    val allWord: Flow<List<Word>> = wordDao.orderBy()

    /* Por defecto Room suspende las consultas fuera del hilo principal, por lo tanto
    no es necesario implementar una funcion suspend.
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }

}