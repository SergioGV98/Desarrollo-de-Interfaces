fun main(){
    var trickTreatFunction = trickOrTreat(true)
    trickTreatFunction()
    trickTreatFunction = trickOrTreat(false)
    trickTreatFunction()
}

/**
 * Funciones de orden superior se pueden inicializar a uan variable
 */
fun trick() {
    println("¡Hay truco!")
}

fun treat() {
    println("¡Tienes un trato!")
}

val trickFunction = ::trick //Hace referencia a la funcion
//Se ejecuta la funcion a traves de la variable

val treatFunction: () -> Unit ={
    println("¡Tienes un trato!")
}

//Funciones anonimas que cuando se inicializan en una variable es un Literal de funcion
val literalMessageFunction = fun (message:String, person:String):Unit= println("Tienes un trato $message $person")
val treatMessageFunction: (message:String, person:String) -> Unit = { m:String, p:String ->
    println("¡Tienes un trato $m $p!")
}

/**
 * Esta funcion devuelve como retorno un tipo de funcion. egun el valor booleano
 * devuelve la funcion trick o treat. ¡¡No se ejecuta la funcion, no hay llamada a la funcion!!
 */

fun trickOrTreat(isTrick : Boolean): () -> Unit {
    if(isTrick)
        return trickFunction
    else
        return treatFunction
}
