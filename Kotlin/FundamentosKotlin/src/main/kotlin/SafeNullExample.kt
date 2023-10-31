fun main (args : Array<String>){
    var name = "Sergio"
    //name = null No se puede inicializar en kotlin cosas a null
    var address : String? = null //Se define en el tipo que la variable acepta nulos
    val size : Int = address?.length ?: 0

    println("Valor de la variable: $address -> Longitud $size")
}