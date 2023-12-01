package com.moronlu18.accounts.network

import java.lang.Exception

//Clase de tipo de E como respuesta del servidor es settings

/**
 * Esta clase sellado representa la respuesta de un servicio API REST, firebase
 * donde se declara la clase Error que almacenara los errores de la infraestructura.
 * y el caso de éxito sea una colección de datos.
 */
sealed class Resource {
    data class Success<T>(var data: T?): Resource()
    data class Error(var exception: Exception): Resource()
}

//Tanto error como success puede tener varias funciones.


//Esto sería código duplicado. Más específico.
sealed class ResourceItem {
    data class Success<Item>(var data: Item?): Resource()
    data class Error(var exception: Exception): Resource()

}