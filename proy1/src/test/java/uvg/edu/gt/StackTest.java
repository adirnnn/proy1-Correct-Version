package uvg.edu.gt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    private Stack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testPush() {
        stack.push(5);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.count());
        assertEquals(5, stack.top());
    }

    @Test
    public void testPop() {
        stack.push(5);
        assertEquals(5, stack.pop());
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.count());
    }

    @Test
    public void testTop() {
        stack.push(5);
        stack.push(10);
        assertEquals(10, stack.top());
        assertFalse(stack.isEmpty());
        assertEquals(2, stack.count());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(5);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testCount() {
        assertEquals(0, stack.count());
        stack.push(5);
        stack.push(10);
        assertEquals(2, stack.count());
        stack.pop();
        assertEquals(1, stack.count());
    }
}