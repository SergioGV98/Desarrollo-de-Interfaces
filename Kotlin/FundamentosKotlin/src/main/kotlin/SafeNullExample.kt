fun main (args : Array<String>){
    var name = "Sergio"
    //name = null No se puede inicializar en kotlin cosas a null
    var address : String? = null //Se define en el tipo que la variable acepta nulos
    val size : Int = address?.length ?: 0

    println("Valor de la variable: $address -> Longitud $size")

    val cat = Cat("Yuki", null)
    println(cat)
    cat.age = 5
    println("La edad del gato ${cat.age} y el chip es ${cat.chip} y el numero de patas ${Cat.NUM_LEG}")

    var safenullString : String? = "Hola"
    safenullString = null

    //La funcio o modimos let, solo ejecuta el bloque cuando la variable sea Not Null.
    safenullString?.let {println("La longitud es ${safenullString.length}")} ?: println("La variable es null")
}