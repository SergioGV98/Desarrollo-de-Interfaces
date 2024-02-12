import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime

class TraficoMalaga : CiudadCentro() {
    override fun obtenerMulta(): BigDecimal? {
        return BigDecimal("1500")
    }

    override fun puedeCircular(placa: String, fechaHora: LocalDateTime): Boolean {
        if (esFinDeSemana(fechaHora) && esHorarioRegulado(fechaHora)) {
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