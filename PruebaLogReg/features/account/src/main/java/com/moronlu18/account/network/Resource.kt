package com.moronlu18.accountsignin.data.network

/**
 * Esta clase sellada representa la respuesta de un servicio API REST, firebase
 * donde se declara la clase Error que almacenara los errores de la infraestructura
 * y el caso de exito sea una coleccion de datos.
 */

sealed class Resource {
    //data class Success<T,E>(var data: T, var settings: E): Resource()
    //data class Success<T>(var data: Collection<T>): Resource()
    data class Success<T>(var data: T): Resource()
    data class Error(var exception: Exception): Resource()
}