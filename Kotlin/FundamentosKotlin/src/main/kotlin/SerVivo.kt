/**
 * Interfaz que define a un ser vivo.
 * Pueden tener propiedades y funciones ya implementadas.
 */

interface SerVivo {
    //Definir una propiedad. Todas las clases que implementen esta interfaz deben
    //dar un valor a tipo.
    val tipo : String

    //Esta funcion esta implementada ya en la interfaz. No es necesario sobrescribir la funcion
    fun viewTipo(){
        println("Soy un ser vivo $tipo")
    }

    //Funciones que deben ser implementadas por todas las clases
    fun born()
    fun died()
    fun eat()
}