package com.moronlu18.account.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.moronlu18.repository.UserRepositoryv2
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private var state = MutableLiveData<UserListState>()
    private var userRepository = UserRepositoryv2
    private var allUser = userRepository.getUserList()?.asLiveData()

    //    private var userRepository
//    val result = userRepository.get
    //result userRepository.getUserList().asLiveData
    //result.value.isEmpty() == true ->


    //Los flow está dando  una secuencia de valores de User. pero puede tener un error en el tema del Resource.

    /*fun getUser():Flow<List<User>>{

        //InvoiceDatabase.getInstance()?.userDao?.All
    }*/

    //ForeignKey(entity = User::class, parent)


    fun getState(): LiveData<UserListState> {
        return state
    }

    /**
     * Función que pide el listado de usuarios al repositorio
     * No tiene que devolver nada
     */
    fun getUserList() {
        //Iniciar la corrutina
        viewModelScope.launch {
            when {
                allUser?.value?.isEmpty() == true -> state.value = UserListState.NoDataError
                else -> {
                    state.value = UserListState.Success
                }
            }
        }
    }

}