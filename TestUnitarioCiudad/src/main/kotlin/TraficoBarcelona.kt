import java.math.BigDecimal
import java.time.LocalDateTime

class TraficoBarcelona : CiudadCentro() {
    override fun obtenerMulta(): BigDecimal? {
        return BigDecimal("3000")
    }

    override fun puedeCircular(placa: String, fechaHora: LocalDateTime): Boolean {
        if(!esPlacaPar(placa) && esHorarioRegulado(fechaHora) && !esDiaPar(fechaHora)){
            return true
        } else if (esPlacaPar(placa) && esHorarioRegulado(fechaHora) && esDiaPar(fechaHora)){
            return true
        }
        return false
    }

    override fun esHorarioRegulado(fechaHora: LocalDateTime): Boolean {
        return (fechaHora.hour >= 6.00 && fechaHora.hour <= 8.30) || (fechaHora.hour >= 15.00 && fechaHora.hour <= 19.30)
    }
}