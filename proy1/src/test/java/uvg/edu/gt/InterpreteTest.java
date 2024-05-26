package uvg.edu.gt;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class InterpreteTest {
    private Interprete interprete;

    @BeforeEach
    public void setUp() {
        interprete = new Interprete();
    }

    @Test
    public void testOperate() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("+");
        commands.add("1");
        commands.add("2");
        String result = interprete.operate(commands, 2);
        assertEquals("3", result);
    }

    @Test
    public void testNewVariable() {
        ArrayList<String> command = new ArrayList<>();
        command.add("setq");
        command.add("x");
        command.add("5");
        String result = interprete.newVariable(command);
        assertEquals("x: 5", result);
    }

    @Test
    public void testCond() {
        ArrayList<String> lisp = new ArrayList<>();
        lisp.add("Cond");
        lisp.add("equals");
        lisp.add("5");
        lisp.add("5");
        lisp.add("'(print 1)");
        lisp.add("'(print 2)");
        String result = interprete.cond(lisp);
        assertEquals("( p r i n t   1 )", result.trim());
    }
}