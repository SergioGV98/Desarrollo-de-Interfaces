/*enum class Directions {
    NORTH, SOUTH, WEST, EAST
}*/


/**
 * Los enumerados pueden tener propiedades y funciones
 */

enum class Directions(val dir: Int) {
    //IMPORTANTE: Se debe añadir ; cuando se declaran funciones en un enumerado
    NORTH(1), SOUTH(-1), WEST(1), EAST(-1);

    //¿Que tengo que poner en when?

    fun description(): String {
        return when (this) {
            NORTH -> "Rey en el Norte"
            SOUTH -> "Como en el sur en ningun lado"
            WEST -> "Me siento perdido"
            EAST -> "Necesito una brujula"
        }
    }
}

/**
 * En este ejemplo se demuestra que un enumerado es una clase, que peude declarar incluso metodos Abstractos.
 * Su uso es evitar el uso del operador when
 */

enum class ProtocolState{
    WAITING{
        override fun signal(): ProtocolState {
            //Se inicializa el objeto
            return WAITING
        }

    },
    TALKING{
        override fun signal(): ProtocolState {
            //Se inicializa el objeto
            return TALKING
        }

    };

    abstract fun signal() : ProtocolState
}

fun main(args: Array<String>) {
    var userDirection: Directions? = null
    println("Direccion que debe tomar mi vida $userDirection")

    userDirection = Directions.NORTH
    println("Propiedad name ${userDirection.name}. Propiedad dir: ${userDirection.dir}. Propiedad ordinal (orden declaracion): ${userDirection.ordinal}")
}