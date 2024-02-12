import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime

class TraficoMalaga : CiudadCentro() {
    override fun obtenerMulta(): BigDecimal? {
        return BigDecimal("1500")
    }

    override fun puedeCircular(placa: String, fechaHora: LocalDateTime): Boolean {
        return when (fechaHora.dayOfWeek) {
            DayOfWeek.MONDAY -> placa.endsWith("1") || placa.endsWith("2") && esHorarioRegulado(fechaHora)
            DayOfWeek.TUESDAY -> placa.endsWith("3") || placa.endsWith("4") && esHorarioRegulado(fechaHora)
            DayOfWeek.WEDNESDAY -> placa.endsWith("5") || placa.endsWith("6") && esHorarioRegulado(fechaHora)
            DayOfWeek.THURSDAY -> placa.endsWith("7") || placa.endsWith("8") && esHorarioRegulado(fechaHora)
            DayOfWeek.FRIDAY -> placa.endsWith("9") || placa.endsWith("0") && esHorarioRegulado(fechaHora)
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> esFinDeSemana(fechaHora)
        }
    }

    override fun esHorarioRegulado(fechaHora: LocalDateTime): Boolean {
        val hour = fechaHora.hour
        return (hour in 6..10) || (hour in 16..20)
    }
}