/**
 * Este fichero contendra las funciones de extension definidas en mi proyecto
 */

fun Person.clone() : Person{
    return Person(this.name, this.surname)
}