import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import java.time.LocalDateTime

class TraficoBarcelonaTest {

    @Test
    //puedeCircular_esFinDeSemana_True()
    fun `es fin de semana`() {
        //Configuracion
        val ciudad = CiudadCentro.getCiudad("Barcelona")
        assertTrue(ciudad.puedeCircular("BA1234", LocalDateTime.of(2024, 2, 10, 6, 10)))
    }

    @Test
    fun `puede circular con placa impar dia impar en horario regulado`(){
        val ciudad = CiudadCentro.getCiudad("Barcelona")
        assertTrue(ciudad.puedeCircular("B1231", LocalDateTime.of(2024, 2, 13, 6, 10)))
    }

    @Test
    fun `no puede circular con placa impar dia impar en horario regulado`(){
        val ciudad = CiudadCentro.getCiudad("Barcelona")
        assertFalse(ciudad.puedeCircular("B1231", LocalDateTime.of(2024, 2, 12, 8, 10)))
    }

    @Test
    fun `puede circular con placa par dia par en horario regulado`(){
        val ciudad = CiudadCentro.getCiudad("Barcelona")
        assertTrue(ciudad.puedeCircular("B1232", LocalDateTime.of(2024, 2, 10, 7, 10)))
    }

    @Test
    fun `no puede circular con placa par dia par en NO horario regulado`(){
        val ciudad = CiudadCentro.getCiudad("Barcelona")
        assertFalse(ciudad.puedeCircular("B1232", LocalDateTime.of(2024, 2, 10, 9, 10)))

    }

    @Test
    fun `obtener multa con cantidad correcta`(){
        val ciudad = CiudadCentro.getCiudad("Barcelona")
        assertEquals(BigDecimal(3000), ciudad.obtenerMulta())
    }

    @Test
    fun `obtener multa con cantidad incorrecta`(){
        val ciudad = CiudadCentro.getCiudad("Barcelona")
        assertNotEquals(BigDecimal(4000), ciudad.obtenerMulta())
    }
}