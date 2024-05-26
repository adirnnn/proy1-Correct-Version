package uvg.edu.gt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrefixCalcTest {
    private PrefixCalc prefixCalc;

    @BeforeEach
    public void setUp() {
        prefixCalc = new PrefixCalc();
    }

    @Test
    public void testCalcularPrefijo_Suma() {
        String expresion = "+ 3 4";
        Integer resultado = prefixCalc.calcularPrefijo(expresion);
        assertEquals(7, resultado);
    }

    @Test
    public void testCalcularPrefijo_Resta() {
        String expresion = "- 10 4";
        Integer resultado = prefixCalc.calcularPrefijo(expresion);
        assertEquals(6, resultado);
    }

    @Test
    public void testCalcularPrefijo_Multiplicacion() {
        String expresion = "* 5 4";
        Integer resultado = prefixCalc.calcularPrefijo(expresion);
        assertEquals(20, resultado);
    }

    @Test
    public void testCalcularPrefijo_Division() {
        String expresion = "/ 20 5";
        Integer resultado = prefixCalc.calcularPrefijo(expresion);
        assertEquals(4, resultado);
    }
}