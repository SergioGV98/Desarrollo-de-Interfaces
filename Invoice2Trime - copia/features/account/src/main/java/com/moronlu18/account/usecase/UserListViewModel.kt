package com.moronlu18.account.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.moronlu18.data.account.User
import com.moronlu18.repository.UserRepositoryQuitar
import com.moronlu18.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private var state = MutableLiveData<UserListState>()


    private var userRepository = UserRepository()

    //La secuencia de usuario, está vinculado al ciclo de vida del viewmodel
    var allUser = userRepository.getUserList().asLiveData()

    //Esto será siempre null si lo dejamos así.
    //private var allUser: LiveData<List<User>>? = MutableLiveData<List<User>>()

    //private var userRepository
    //val result = userRepository.get
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
     */
    fun getUserList() {


        //Iniciar la corrutina
        viewModelScope.launch {
            //La carga de los datos es asíncrono y se realiza en la creación del viewModelo.
            // No cuando la vista llama al método GetUserList
            UserRepository.getUserList()

            when {
                allUser.value?.isEmpty() == true -> state.value = UserListState.NoDataError
                else -> state.value = UserListState.Success
            }
        }
    }

    fun sortNatural() {
        UserRepositoryQuitar.dataSet.sort()
    }

    fun sortPreestablecido() {
        UserRepositoryQuitar.dataSet.sortBy { it.email.toString().lowercase() }
    }

    fun delete(user: User) {
        viewModelScope.launch (Dispatchers.IO) {
            userRepository.delete(user)
        }
    }
}