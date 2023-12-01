package com.moronlu18.accounts.repository


import com.moronlu18.accounts.entity.User
import com.moronlu18.accounts.network.Resource
import com.moronlu18.accounts.network.ResourceList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.ArrayList

//import com.example.pruebasconclientes.data.model.User

/**
Esta clase es accesible en tod0 el proyecto. No se puede crear objetos de esta clase
constructor privado. Y tiene un objeto que contiene el listado de usuarios.
 */


//El repositorio actualmente es estático
class UserRepository private constructor() { //Estático
    //Cuando usa UserRepository. llama a todos los métodos

    companion object {

        var dataSet: MutableList<User> = mutableListOf()

        init {
            initDataSetUser()
        }

        private fun initDataSetUser(): MutableList<User> {
            dataSet.add(User("Alejandro", "López", "cb2@hotmail.es"))
            dataSet.add(User("Paella", "1234", "vfrv2@hotmail.es"))
            dataSet.add(User("Cebolla", "Veeee", "cbb@hotmail.es"))
            dataSet.add(User("Rabano", "Ra", "rf@hotmail.es"))
            dataSet.add(User("Alejandro", "López", "cb2@hotmail.es"))
            dataSet.add(User("Paella", "1234", "vfrv2@hotmail.es"))
            dataSet.add(User("Cebolla", "Veeee", "cbb@hotmail.es"))
            dataSet.add(User("Rabano", "Ra", "rf@hotmail.es"))
            return dataSet
        }


        /**
         * La función que se pregunta a Firebase /Room (Sqlite por el usuario)
         */
        //al ser una corrutina se hace suspend
        suspend fun login(email: String, password: String): Resource {
            //Este código se ejecuta en un hilo específico para operaciones entrada/salida IO
            withContext(Dispatchers.IO) {
                delay(3000)
                /*
                Se ejecutará el código de consulta a Firebase que puede tardar más de 5sg y en ese
                caso se obtiene el error ANR(Android Not Responding) porque puede bloquear la vista

                para garantizar que no falle
                */

                /*return Resource.Success(
                    data = Account.st
                )*/

            }

            return Resource.Error(Exception("El password es incorrecto"))
        }

        fun getUser(user: User): Boolean{
            dataSet.find { user.email == it.email }.let { return true }//^^
        }

        suspend fun getUserList(): ResourceList {
            return withContext(Dispatchers.IO){
                delay(2000)
                when{
                    dataSet.isEmpty() -> ResourceList.Error(Exception("No hay datos"))
                    else -> ResourceList.Success(dataSet as ArrayList<User>)
                }
            }
        }

    }


}
