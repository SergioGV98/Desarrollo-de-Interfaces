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

