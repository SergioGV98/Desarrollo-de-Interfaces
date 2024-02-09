import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime

/**
 * Clase que implementa la multa que obtiene un coche cuando entra en el centro de una ciudad y si un coche puede circular.
 */
abstract class CiudadCentro {
    abstract fun obtenerMulta(): BigDecimal?
    abstract fun puedeCircular(placa: String, fechaHora: LocalDateTime): Boolean
    abstract fun esHorarioRegulado(fechaHora: LocalDateTime): Boolean


    fun esDiaPar(fechaHora: LocalDateTime?): Boolean {
        return fechaHora!!.dayOfMonth % 2 == 0
    }

    fun esPlacaPar(placa: String?): Boolean {
        return Character.getNumericValue(placa!![placa.length - 1]) % 2 == 0
    }

    fun esFinDeSemana(fechaHora: LocalDateTime?): Boolean {
        return fechaHora!!.dayOfWeek == DayOfWeek.SATURDAY || fechaHora.dayOfWeek == DayOfWeek.SUNDAY
    }

    fun placaNumero(placa: String): String{
        return placa.subSequence(0, placa.length-4).toString()
    }


    companion object Builder {
        fun getCiudad(ciudad: String): CiudadCentro {
            return when (ciudad) {
                "Madrid" -> TraficoMadrid()
                "Malaga" -> TraficoMalaga()
                "Barcelona" -> TraficoBarcelona()
                else -> throw UnsupportedOperationException("La ciudad $ciudad no es soportada.")
            }
        }
    }
}

fun TraficoMadrid() = object : CiudadCentro() {
    override fun obtenerMulta(): BigDecimal? {
        return BigDecimal("0")
    }

    override fun puedeCircular(placa: String, fechaHora: LocalDateTime): Boolean {
        return true
    }

    override fun esHorarioRegulado(fechaHora: LocalDateTime): Boolean {
        return true
    }

}

fun TraficoMalaga() = object : CiudadCentro(){
    override fun obtenerMulta(): BigDecimal? {
        return BigDecimal("1500")
    }

    override fun puedeCircular(placa: String, fechaHora: LocalDateTime): Boolean {
        if (esFinDeSemana(fechaHora)) {
            return true
        }

        return when (fechaHora.dayOfWeek) {
            DayOfWeek.MONDAY -> placaNumero(placa).endsWith("1") || placaNumero(placa).endsWith("2")
            DayOfWeek.TUESDAY -> placaNumero(placa).endsWith("3") || placaNumero(placa).endsWith("4")
            DayOfWeek.WEDNESDAY -> placaNumero(placa).endsWith("5") || placaNumero(placa).endsWith("6")
            DayOfWeek.THURSDAY -> placaNumero(placa).endsWith("7") || placaNumero(placa).endsWith("8")
            DayOfWeek.FRIDAY -> placaNumero(placa).endsWith("9") || placaNumero(placa).endsWith("0")
            else -> false
        }
    }

    override fun esHorarioRegulado(fechaHora: LocalDateTime): Boolean {
        val hour = fechaHora.hour
        return (hour in 6..10) || (hour in 16..20)
    }

}

fun TraficoBarcelona() = object : CiudadCentro() {
    override fun obtenerMulta(): BigDecimal? {
        return BigDecimal("3000")
    }

    override fun puedeCircular(placa: String, fechaHora: LocalDateTime): Boolean {
        return esPlacaPar(placa) && (esHorarioRegulado(fechaHora) && esDiaPar(fechaHora))
    }

    override fun esHorarioRegulado(fechaHora: LocalDateTime): Boolean {
        return (fechaHora.hour >= 6.00 && fechaHora.hour <= 8.30) || (fechaHora.hour >= 15.00 && fechaHora.hour <= 19.30)
    }
}