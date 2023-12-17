package com.sergiogv98.loginSimple.repository

import com.sergiogv98.loginSimple.data.Usuario
import com.sergiogv98.loginSimple.enums.TypeUser

class UserRepository {

    companion object {
        var dataSet: MutableList<Usuario> = mutableListOf()

        init {
            initDataSetUser()
        }

        private fun initDataSetUser() {
            dataSet.add(Usuario(1, "Sergio", "Sergio@gmail.com", "1234", TypeUser.ADMINISTRADOR))
        }
    }
}