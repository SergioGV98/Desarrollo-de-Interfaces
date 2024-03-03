package com.example.repasoexamenlourdes.usecase

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repasoexamenlourdes.data.Juego
import com.example.repasoexamenlourdes.repository.GameRepositoryDB
import com.example.repasoexamenlourdes.ui.GameCreationState

class GameCreationViewModel: ViewModel() {

    var gameName = MutableLiveData<String>()
    private var repositoryDB = GameRepositoryDB()
    private var state = MutableLiveData<GameCreationState>()

    fun validateGame(){
        when{
            TextUtils.isEmpty(gameName.value) -> state.value = GameCreationState.NameIsMandatory
            gameName.value!!.length > 10 -> state.value = GameCreationState.NameIsToLong
            else -> {
                state.value = GameCreationState.OnSuccess
            }
        }
    }

    fun insert(juego: Juego){
        repositoryDB.insert(juego)
    }

    fun getState(): LiveData<GameCreationState>{
        return state
    }
}