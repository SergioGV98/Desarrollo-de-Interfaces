import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.UnsupportedOperationException

class CiudadCentroTest{
    @Test
    fun `ciudad no soportada`(){
        assertThrows(UnsupportedOperationException::class.java){
            CiudadCentro.getCiudad("Sevilla")
        }
    }
}