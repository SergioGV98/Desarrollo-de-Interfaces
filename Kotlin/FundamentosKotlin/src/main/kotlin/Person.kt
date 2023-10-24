//Se define el constructor primario en la difinicion de la clase
class Person (name: String, surname: String) {
    //Se puede utilizar init para inicializar una propiedad del constructor
    var nameUppercase:String
    //Edad no forma parte del constructor primario
    var age: Int = 0
    init {
        nameUppercase=name.toUpperCase()
    }
}