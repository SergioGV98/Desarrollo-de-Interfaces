package com.moronlu18.accounts.repository

import com.moronlu18.accounts.entity.User
import com.moronlu18.accounts.network.Resource
import com.moronlu18.accounts.network.ResourceList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.ArrayList

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
            /*dataSet.add(User("Alejandro", "López", "cb2@hotmail.es"))
            dataSet.add(User("Paella", "1234", "vfrv2@hotmail.es"))
            dataSet.add(User("Cebolla", "Veeee", "cbb@hotmail.es"))
            dataSet.add(User("Rabano", "Ra", "rf@hotmail.es"))
            dataSet.add(User("Alejandro", "López", "cb2@hotmail.es"))
            dataSet.add(User("Paella", "1234", "vfrv2@hotmail.es"))
            dataSet.add(User("Cebolla", "Veeee", "cbb@hotmail.es"))
            dataSet.add(User("Rabano", "Ra", "rf@hotmail.es"))*/
        }


        /**
         * La función que se pregunta a Firebase /Room (Sqlite por el usuario)
         */
        suspend fun login(email: String, password: String): Resource {
            withContext(Dispatchers.IO) {
                delay(3000)

            }

            return Resource.Error(Exception("El password es incorrecto"))
        }

        /**
         * Esta función simula una consulta asíncrono y devuelve el conjunto de
         * usuarios de la aplicación
         */
        suspend fun getUserList(): ResourceList{
            return withContext(Dispatchers.IO){
                delay(2000)
                when{
                    dataSet.isEmpty()-> ResourceList.Error(Exception("No hay datos"))
                    else -> ResourceList.Success(dataSet as ArrayList<User>)
                }
            }
        }


    }


}
