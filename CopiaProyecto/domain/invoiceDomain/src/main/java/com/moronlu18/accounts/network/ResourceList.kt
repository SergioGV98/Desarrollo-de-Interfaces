package com.moronlu18.accounts.network

import java.lang.Exception

//Clase de tipo de E como respuesta del servidor es settings

/**
 * Esta clase sellado representa la respuesta de un servicio API REST, firebase
 * donde se declara la clase Error que almacenara los errores de la infraestructura.
 * y el caso de éxito sea una colección de datos.
 */
sealed class ResourceList {
    data class Success<T>(var data: ArrayList<T>?): ResourceList()
    data class Error(var exception: Exception): ResourceList()
}
