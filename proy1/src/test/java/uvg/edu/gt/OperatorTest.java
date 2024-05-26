package uvg.edu.gt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperatorTest {
    private Operator operator;

    @BeforeEach
    public void setUp() {
        operator = new Operator();
    }

    @Test
    public void testEquals() {
        String input = "= 5 5";
        String result = operator.equals(input);
        assertEquals("True", result);
    }

    @Test
    public void testIsSmaller() {
        String lisp = "< 3 5";
        String result = operator.isSmaller(lisp);
        assertEquals("True", result);
    }

    @Test
    public void testIsBigger() {
        String lisp = "> 8 5";
        String result = operator.isBigger(lisp);
        assertEquals("True", result);
    }

    @Test
    public void testIsAtom() {
        String lisp = "Atom 5";
        String result = operator.isAtom(lisp);
        assertEquals("True", result);
    }

    @Test
    public void testIsList() {
        String lisp = "List (a b c)";
        String result = operator.isList(lisp);
        assertEquals("NIL", result);
    }
}