import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Random

/**
 * Launch simplemente suspende el codigo, liberando el hilo subyacente donde
 * se ejecuta el codigo para otros usos o tareas.
 * runBlocking bloquea el hilo actual. Se utiliza siempre que se necesite realizar
 * tareas secuenciales (entorno pruebas y peticiones al servidor)
 */

fun main(){
    GlobalScope.launch{
        delay(4000)
        println("Hola desde la corrutina 1 en ${Thread.currentThread().name}")
    }
    GlobalScope.launch{
        delay(2000)
        println("Hola desde la corrutina 2 en ${Thread.currentThread().name}")
    }

    Thread.sleep(3000)

    //El siguiente codigo se ejecuta de forma secuencial
    runBlocking {
        val d1 = dato()
        println("Primera ejecucion de la funcion dato() = $d1")
        val d2 = dato()
        println("Segunda ejecucion de la funcion dato() = $d2")
    }
}

fun dato(): Int {
    return Random(2000).nextInt()
}
