/**
 * Este fichero contiene ejemplos de funciones en Kotlin
 */

fun main() {
    ejemploFuncionParametro()

}

fun ejemploPredicado(){
    //Vamos a utilizar la funcion count de un array enteros y le vamos a pasar como predicado
    //una funcion y otra ejemplo con expresion lambda
    val list = listOf(24, 6, 44, 10, 3, 8, 9)
    val resultFunction = list.count(
        fun(number:Int):Boolean{
            return number%2==0
        }
    )

    //Expresion Lambda
    val resultLambda = list.count {
        println("Esto es una expresion lambda. Con parametros: $it")
        it%2==0//En una expresion lambda no hay return. Es valor que se devuelve es la ultima linea
    }
    println("Los numeros con resto 0 son $resultLambda")
}

/**
 * Las funciones pueden tener una funcion como argumento. Vamos definir un metodo opera que
 * dada una lista realizara la operacion de la funcion con un valor y devuelve
 * una lista.
 */
fun ejemploFuncionParametro(){

    //Literal de funcion
    val mul = fun (x:Int, y:Int): Int = x*y
    val initList : ArrayList<Int> = arrayListOf(3, 4, 5, 6, 7)
    println("Lista original: $initList")

    val sumList = opera (initList, 5, ::sum) //Referencia a funcion
    println("Lista Suma: ${sumList.joinToString (",")}")

    val mulList = opera (initList, 5, mul)
    println("Lista Multiplicacion: ${mulList.joinToString (",")}")

    val restList = opera (initList, 5, fun(x, y):Int=x-y)
    println("Lista Resta: ${restList.joinToString (",")}")

    //val divList = opera (initList, 5, { x, y -> x / y })
    val divList = opera (initList, 5) { x, y -> x / y }
    println("Lista Division: ${divList.joinToString (",")}")

}

fun opera(list: ArrayList<Int>, valor:Int, operaFun:(Int, Int) -> Int):ArrayList<Int>{
    val result = arrayListOf<Int>()
    for(item in list){
        result.add(operaFun(valor, item))
    }
    return result
}

//En el caso de la suma voy a crear la funcion
fun sum (x:Int, y:Int): Int = x+y



