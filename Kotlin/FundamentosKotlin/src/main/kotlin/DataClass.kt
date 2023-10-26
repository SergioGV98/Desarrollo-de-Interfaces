fun main(){
    dataclass()
}

fun dataclass() {
    //Constructor primario
    val p1 = Person("Sergio", "Garcia Vico")

    //Constructor secundario
    val p2 = Person("Juan")

    //Constructor vacio
    val p3 = Person()

    println(p1)
    println(p2)

    val student1 = Student("Sergio", "Garcia Vico", "")
    student1.age = 18
    println(student1)

    //Igual por estructura, es que son objetos diferentes pero contienen
    // los mismos valores que yo he considerado en la funcion equals
    val igualContenido = if (p1 == student1) true else false // Similar al equals de Java
    val igualReferencia = if(p1 === student1) true else false

    println(igualContenido)
    println(igualReferencia)
}

