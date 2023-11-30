package com.moronlu18.repository

import com.moronlu18.accounts.entity.User
import com.moronlu18.accountsignin.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
Esta clase es accesible en tod0 el proyecto. No se puede crear objetos de esta clase
constructor privado. Y tiene un objeto que contiene el listado de usuarios.
 */

class UserRepository private constructor() {


    companion object {
        var dataSet: MutableList<User> = mutableListOf()

        init {
            initDataSetUser()
        }

        private fun initDataSetUser() {
            var dataSet: MutableList<User> = ArrayList()
            dataSet.add(User("Alejandro", "López", "cb2@hotmail.es"))
            dataSet.add(User("Paella", "1234", "vfrv2@hotmail.es"))
            dataSet.add(User("Cebolla", "Veeee", "cbb@hotmail.es"))
            dataSet.add(User("Rabano", "Ra", "rf@hotmail.es"))
            dataSet.add(User("Alejandro", "López", "cb2@hotmail.es"))
            dataSet.add(User("Paella", "1234", "vfrv2@hotmail.es"))
            dataSet.add(User("Cebolla", "Veeee", "cbb@hotmail.es"))
            dataSet.add(User("Rabano", "Ra", "rf@hotmail.es"))
        }

        suspend fun login(email: String, password: String): Resource {
            //Este codigo se ejecuta en un hilo especifico para operaciones entrada/salida (IO)
            return withContext(Dispatchers.IO){
                //delay(3000)
                //Se ejecutara el codigo de consulta a FireBase que puede tardar mas de 5sg y en ese
                //caso se obtiene el error ANR (Android Not Responding) porque puede bloquear la vista.
                AuthFirebase().login(email, password)
            }
        }
    }

}