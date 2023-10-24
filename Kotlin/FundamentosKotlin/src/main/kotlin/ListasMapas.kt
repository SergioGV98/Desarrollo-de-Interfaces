import javax.swing.JOptionPane

/**
 * En este fichero se explicara la creacion de listas dinamicas y mapas de solo lectura
 * o mutables
 */

fun main(args:Array<String>){
    //crearListConsola()
    //crearModoGrafico()
    crearMapaLectura()
}

fun crearListMutable(){
    var lista = arrayListOf(3, 2, 4, 5, 6, 1, 0)
    lista.add(9)
    lista.add(12)
    lista.remove(5)
    print(lista.joinToString { ", " })
}

fun crearListLectura(){
    var listaLectura = listOf(5,6,7,8,9)
    print(listaLectura.joinToString { ", " })
}

/**
 * Esta funcion devuelve un valor Int porque se indica al final de la cabecera
 */

fun cargar():Int{
    print("Ingrese un numero: ")
    return readln().toInt()
}

fun crearListConsola() {
    var lista = ArrayList<Int>()

    var listaInterfaz: List<Int> = List(5) { cargar() }
    /*
    for(i in 0..< 4){
        lista.add(cargar())
    }

    */

    println(listaInterfaz.joinToString())
}

fun crearModoGrafico() {
    var listaDinamica = ArrayList<Int>()
    var respuesta:Char

    do{
        listaDinamica.add(JOptionPane.showInputDialog("Ingresa un numero").toInt())
        respuesta = JOptionPane.showInputDialog("¿Quieres continuar s/n?")[0]

    }while (respuesta == 's' || respuesta == 'S' || respuesta == '0')

    //Se imprime la lista dinamica a traves de la funcion foreach
    listaDinamica.forEach{ println(it) }
}

fun crearMapaLectura() {
    //Crear un mapa vacio
    var mapa: Map<String, Int> = mapOf()
    println(mapa)
    //Crear mapa mutable
    var mapaMutable: MutableMap<String, Int> = mutableMapOf()
    mapaMutable["Hola"] = 1 // Mas eficiente
    mapaMutable.put("Adios", 2) // Menos eficiente
    //Crear mapa mutable e inicializar el mapa directamente
    var mapaMutableInicializado = mutableMapOf("Lourdes" to 1, "Juan" to 2, "Mario" to 3)
    //Recorrer mapa
    mapaMutableInicializado.forEach{ println(it.key + " - " + it.value) }
}
