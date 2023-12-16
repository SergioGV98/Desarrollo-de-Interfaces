package com.sergiogv98.loginSimple.data

import com.sergiogv98.loginSimple.enums.TypeUser

data class Usuario (val idUser: Int, val name: String, val email: String, val password: String, val typeUser: TypeUser)