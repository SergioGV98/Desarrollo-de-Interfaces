import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime

class TraficoMadridTest {
    @Test
    fun `obtener multa con cantidad correcta`(){
        val ciudad = CiudadCentro.getCiudad("Madrid")
        assertEquals(BigDecimal(0), ciudad.obtenerMulta())
    }

    @Test
    fun `obtener multa con cantidad incorrecta`(){
        val ciudad = CiudadCentro.getCiudad("Madrid")
        assertNotEquals(BigDecimal(4000), ciudad.obtenerMulta())
    }

    @Test
    fun `siempre puede circular`(){
        val ciudad = CiudadCentro.getCiudad("Madrid")
        assertTrue(ciudad.puedeCircular("MAD1234", LocalDateTime.now()))
    }

}