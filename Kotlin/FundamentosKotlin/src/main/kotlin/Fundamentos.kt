import java.lang.StringBuilder

fun main(args: Array<String>) {
    //variablesyconstantes()
    //tiposdedatos()
    //trabajoconcadenas()
    //println(sentenciawhen())
    trabajoconarray()
}

/**
 * Funcion que crea variables mutables e inmutables
 */

fun variablesyconstantes() {
    //En este caso se declara tipo variable y se inicializa. Aqui el compilador no infiere el tipo y es mutable (Se puede modificar).
    var name: String = "Sergio"
    //El compilador infiere el tipo y es inmutable (No se puede modificar).
    val surname = "Garcia Vico"
    println("Nombre y apellidos: " + name + " " + surname)
    println("Nombre y apellidos: $name $surname")
    val age = 18
    when(age){
        0,1,2,3 -> println("Es un bebe")
        in 4..10 -> println("Es un niño/a")
        in 11..20-> println("Adolescente")
        in 21..70 -> println("En la flor de la vida")
        in 71..90 -> println("Jubilado")
        else -> println("Muerto")
    }
}

fun tiposdedatos(){
    //OPERACIONES DE NUMERO
    val num1 : Int = 32768
    val num2 = 14
    val sum = num1+num2
    println("Suma: $sum")

    //CONVERSIONES
    //1. No se puede pasar de Int (32) a Short (16) porque se peirden datos
    //val numshort : Short = num1
    val numshort : Short = num1.toShort()
    println("Resultado de conversion explicita INT -> Short. Numero int: $num1 Numero short: $numshort")

    //2. En JAVA esto es una conversion implicita.
    //Kotlin: No se puede pasar de Short (16) a Int (32) porque son TIPOS distintos
    //val numint : Int = numshort

    //3. Se debe realizar una conversion explicita
    val numshort2 : Short=1
    val numint : Int = numshort2.toInt()
    println("Resultado de conversion explicita Short -> Int. Numero short: $numshort2 Numero int: $numint")

    //Operaciones con boolean
    val inteligente = true
    val fea : Boolean = false
    println(inteligente == fea)
    println(inteligente && fea)

    //Char se defien con comilla simple
    val char : Char = 'A'
    val response = 'Y'
    //Conversion de char a Int
    val numchar = response.code
    println("Valor char: $response Valor Int: $numchar")
}

fun trabajoconcadenas(){
    //Estas variables son de tipo String
    val a = "HOLA"
    val b = "Mundo"
    val saludo = a.plus(" o quizas adios, ").plus(b)
    //Para imprimir utilizamos plantillas de cadenas
    println("Mensaje con String: $a o quizas adios $b")

    //Hay una clase mas eficiente para el manejo de cadenas StringBuilder
    val builder = StringBuilder()
    builder.append("HOLA").append(" o quizas adios, ").append("Mundo")
    println("Mensaje Builder: $builder")
}

fun sentenciawhen(): String {
    var s = "Me gusta el juego fifa"
    var x = 700
    val text : String= when {
        s.contains("fifaa") -> "es un gamer!"
        x > 599 -> "Esto es muy caro"
        s is String -> "Es un String!"
        else -> ""
    }
    return ("Resultado final $text")
}

fun trabajoconarray(){
    val dias = arrayOfNulls<String>(7)
    dias[0] = "Lunes"
    dias[1] = "Martes"
    dias[2] = "Miercoles"
    dias[3] = "Jueves"
    dias[4] = "Viernes"
    dias[5] = "Sabado"
    dias[6] = "Domingo"

    val vacaciones = arrayOfNulls<String>(7)

    val meses : Array<String?>
    meses = arrayOf("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")

    for(mes in meses){
        /*
        if (mes.equals("Diciembre"))
            print("$mes")
        else
            println("$mes")
        */
        //print("${if (mes.equals("Diciembre")) "$mes \n" else "$mes, " }")
        println(if (mes.equals("Diciembre")) "$mes \n" else "$mes, ")
    }
    println(meses.joinToString(" - "))

    var loteria = intArrayOf(23, 25, 67, 34, 67, 98)
    loteria[2] = 1
}