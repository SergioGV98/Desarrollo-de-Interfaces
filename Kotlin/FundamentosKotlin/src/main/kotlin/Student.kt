import java.util.UUID

class Student(name: String, surname: String, innerid: String) : Person(name, surname) {

    val innerid : String

    init {
        this.innerid = UUID.randomUUID().toString()
    }

    override fun work(){
        println("No trabajo, estoy estudiante")
    }

    override fun toString(): String {
        return "${super.toString()} $innerid"
    }
}