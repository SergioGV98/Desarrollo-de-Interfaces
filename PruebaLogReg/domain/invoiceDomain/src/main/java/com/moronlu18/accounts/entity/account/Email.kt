package com.moronlu18.accounts.entity.account

import com.moronlu18.accounts.entity.account.AccountException
import java.util.regex.Pattern

/**
 * Esta clase comprueba que el Email cumple el patron establecido en el
 * metodo compile. En caso constrario lanza una excepcion.
 */

data class Email(val value: String){
    private val pattern = Pattern.compile("")
    init {
        if(!pattern.matcher(value).matches())
            throw AccountException.InvalidEmailFormat()
    }
}
