package com.moronlu18.accountsignin.usecase

import androidx.lifecycle.ViewModel
import com.moronlu18.accountsignin.data.repository.UserRepository

class AccountUseCase: ViewModel() {

    fun getList(){
        //En base a las reglas de negocio
        UserRepository.dataSet;
    }

}