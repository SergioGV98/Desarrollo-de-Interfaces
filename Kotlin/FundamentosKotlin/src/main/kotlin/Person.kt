//Se define el constructor primario en la difinicion de la clase
open class Person (name: String, surname: String) : SerVivo, Mamifero() {
    var age:Int
    var name:String
    var surname:String

    init {
        this.name = name.uppercase()
        this.surname = surname
        this.age = 0
    }

    // Constructor secundario con parametros diferentes al primario
    constructor(name: String) : this (name, "")
    // Constructor secundario y contructor vacio
    constructor() : this("","")

    //region Funciones de la implementacion de SerVivo
    override val tipo: String = "Soy una persona"

    override fun born() {
        println("Ya he nacido")
    }

    override fun died() {
        println("Adios mundo cruel")
    }

    override fun eat() {
        println("Vivan los camperos")
    }
    //endregion

    //region Funciones a implementar de la herencia Mamifero
    override fun expire() {
        println("Respiro con los pulmones")
    }
    //endregion

    //region Equals



    //endregion

    override fun toString(): String {
        return "Person(age=$age, name='$name', surname='$surname', tipo='$tipo')"
    }

    open fun work(){
        println("Soy una persona que trabaja")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        //if (other !is Person) return false

        other as Person

        if (name != other.name) return false
        if (surname != other.surname) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + surname.hashCode()
        return result
    }


}