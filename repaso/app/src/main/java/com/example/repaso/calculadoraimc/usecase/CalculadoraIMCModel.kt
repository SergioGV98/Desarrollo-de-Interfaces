package com.example.repaso.calculadoraimc.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repaso.calculadoraimc.CalculadoraIMCState

class CalculadoraIMCModel : ViewModel() {

    var weight = MutableLiveData<Int>()
    var age = MutableLiveData<Int>()

    private var state = MutableLiveData<CalculadoraIMCState>()

    fun getState(): LiveData<CalculadoraIMCState>{
        return state
    }

}