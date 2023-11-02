package utils

import Person

/**
 * Esta funcion es de nivel superior porque se define en un fichero y es accesible en el package
 * por defecto
 */

fun Person.clone() : Person {
    return Person(this.name, this.surname)
}