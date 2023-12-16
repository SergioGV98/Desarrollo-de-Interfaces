package com.moronlu18.accounts.entity


//Para hacer comparaciones hay que poner el Comparable.
/**
 * Al utilizar data class se implmenta de for automática el método equals, toString, copy y HashCode
 * Teniendo en cuanta las propiedades declaradas en el constructor primerario/principal.
 *
 */
/*
Si quitamos la palabras no sabremos si los objetos son iguales

 */
open class User(open val name: String, open val surname: String, open val email: String) : Comparable<User> {
    override fun compareTo(other: User): Int {
        return name.compareTo(other.name)
    }

}
