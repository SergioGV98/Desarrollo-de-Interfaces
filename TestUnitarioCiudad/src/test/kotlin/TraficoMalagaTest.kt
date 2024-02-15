import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.Month

class TraficoMalagaTest {

    @Test
    fun puedeCircular() {
        val ciudad = CiudadCentro.getCiudad("Malaga")
        assertTrue(ciudad.puedeCircular("MA1234", LocalDateTime.of(2024, 2, 10, 6, 10)))
    }

    @Test
    fun `es fin de semana`() {
        val ciudad = CiudadCentro.getCiudad("Malaga")
        assertTrue(ciudad.puedeCircular("MA1234", LocalDateTime.of(2024, 2, 3, 9, 10)))
    }

    @Test
    fun `es lunes y matricula acaba en 1 o 2`() {
        val ciudad = CiudadCentro.getCiudad("Malaga")
        assertTrue(ciudad.puedeCircular("MA1231", LocalDateTime.of(2024, 2, 5, 9, 10)))
    }

    @Test
    fun `es martes y matricula acaba en 3 o 4`() {
        val ciudad = CiudadCentro.getCiudad("Malaga")
        assertTrue(ciudad.puedeCircular("MA1233", LocalDateTime.of(2024, 2, 6, 9, 10)))
    }

    @Test
    fun `es miercoles y matricula acaba en 5 o 6`() {
        val ciudad = CiudadCentro.getCiudad("Malaga")
        assertTrue(ciudad.puedeCircular("MA1235", LocalDateTime.of(2024, 2, 7, 9, 10)))
    }

    @Test
    fun `es jueves y matricula acaba en 7 o 8`() {
        val ciudad = CiudadCentro.getCiudad("Malaga")
        assertTrue(ciudad.puedeCircular("MA1237", LocalDateTime.of(2024, 2, 8, 9, 10)))
    }

    @Test
    fun `es viernes y matricula acaba en 9 o 0`() {
        val ciudad = CiudadCentro.getCiudad("Malaga")
        assertTrue(ciudad.puedeCircular("MA1230", LocalDateTime.of(2024, 2, 9, 9, 10)))
    }

    @Test
    fun `obtener multa con cantidad correcta`(){
        val ciudad = CiudadCentro.getCiudad("Malaga")
        assertEquals(BigDecimal(1500), ciudad.obtenerMulta())
    }

    @Test
    fun `obtener multa con cantidad incorrecta`(){
        val ciudad = CiudadCentro.getCiudad("Malaga")
        assertNotEquals(BigDecimal(4000), ciudad.obtenerMulta())
    }

}