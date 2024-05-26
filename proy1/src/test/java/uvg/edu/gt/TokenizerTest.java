package uvg.edu.gt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TokenizerTest {
    private Tokenizer tokenizer;

    @BeforeEach
    public void setUp() {
        tokenizer = new Tokenizer();
    }

    @Test
    public void testStringToChar() {
        String command = "(setq x 10)";
        ArrayList<Character> expected = new ArrayList<>();
        expected.add('(');
        expected.add('s');
        expected.add('e');
        expected.add('t');
        expected.add('q');
        expected.add(' ');
        expected.add('x');
        expected.add(' ');
        expected.add('1');
        expected.add('0');
        expected.add(')');
        ArrayList<Character> result = tokenizer.stringToChar(command);
        assertEquals(expected, result);
    }

    @Test
    public void testTokens() {
        String command = "(setq x 10)";
        ArrayList<String> expected = new ArrayList<>();
        expected.add("setq");
        expected.add("x");
        expected.add("10");
        ArrayList<String> result = tokenizer.Tokens(command);
        assertEquals(expected, result);
    }

    @Test
    public void testGetCommandType() {
        ArrayList<String> setqCommand = new ArrayList<>();
        setqCommand.add("setq");
        assertEquals(1, tokenizer.getCommandType(setqCommand));

        ArrayList<String> arithmeticCommand = new ArrayList<>();
        arithmeticCommand.add("+");
        assertEquals(2, tokenizer.getCommandType(arithmeticCommand));

        ArrayList<String> printCommand = new ArrayList<>();
        printCommand.add("print");
        assertEquals(3, tokenizer.getCommandType(printCommand));

        ArrayList<String> comparisonCommand = new ArrayList<>();
        comparisonCommand.add(">");
        assertEquals(4, tokenizer.getCommandType(comparisonCommand));

        ArrayList<String> equalsCommand = new ArrayList<>();
        equalsCommand.add("=");
        assertEquals(6, tokenizer.getCommandType(equalsCommand));

        ArrayList<String> atomCommand = new ArrayList<>();
        atomCommand.add("Atom");
        assertEquals(7, tokenizer.getCommandType(atomCommand));

        ArrayList<String> listCommand = new ArrayList<>();
        listCommand.add("List");
        assertEquals(8, tokenizer.getCommandType(listCommand));

        ArrayList<String> condCommand = new ArrayList<>();
        condCommand.add("Cond");
        assertEquals(9, tokenizer.getCommandType(condCommand));

        ArrayList<String> defunCommand = new ArrayList<>();
        defunCommand.add("defun");
        assertEquals(10, tokenizer.getCommandType(defunCommand));

        ArrayList<String> unknownCommand = new ArrayList<>();
        unknownCommand.add("unknown");
        assertEquals(-1, tokenizer.getCommandType(unknownCommand));
    }
}