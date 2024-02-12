import java.math.BigDecimal
import java.time.LocalDateTime

class TraficoMadrid: CiudadCentro() {
    override fun obtenerMulta(): BigDecimal? {
        return BigDecimal("0")
    }

    override fun puedeCircular(placa: String, fechaHora: LocalDateTime): Boolean {
        return esHorarioRegulado(fechaHora)
    }

    override fun esHorarioRegulado(fechaHora: LocalDateTime): Boolean {
        return true
    }
}