package com.moronlu18.invoice.data.repository

import com.moronlu18.invoice.data.model.User

/**
 * Esta clase es accesible en todo el proyecto. No se pueden crear objetos de esta
 * clase, constructor privado. Y tiene un objeto que contiene el listado de usuario.
 */
class UserRepository private constructor() {

    companion object{
        val dataSet:MutableList<User> = initUpDataSetUser()
        private fun initUpDataSetUser(): MutableList<User> {
            var dataSet: MutableList<User> = ArrayList()
            dataSet.add(User("Sergio", "Garcia", "sergio@iesportada.org"))
            dataSet.add(User("Paco", "Ruiz", "Paco@iesportada.org"))
            dataSet.add(User("Antonio", "Paca", "Antonio@iesportada.org"))
            dataSet.add(User("Juan", "antonio", "Juan@iesportada.org"))
            dataSet.add(User("Miguel", "jesus", "Miguel@iesportada.org"))
            dataSet.add(User("Juanfran", "ruiz", "Juanfran@iesportada.org"))
            return dataSet
        }
    }

}