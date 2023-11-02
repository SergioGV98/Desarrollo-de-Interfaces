data class Cat(val nickname : String, val chip : Int?, val owner : String?){
    var age : Int = 0
        set(value) {
          field = when (value){
              1->12
              2->24
              else -> 24 + ((value-2)*4)
          }
        }

    //Constructor secundario
    constructor(nickname: String, chip: Int?): this(nickname, chip, null)

    //Un objeto acompañante de la clase CAT. Un objeto comun a todas las instancias de GATO
    //No existe el concepto STATIC
    companion object{
        const val NUM_LEG = 4
    }
}