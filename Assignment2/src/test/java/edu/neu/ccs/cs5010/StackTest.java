package edu.neu.ccs.cs5010;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import java.util.*;

public class StackTest {
    private static IStack stack = null;

    @BeforeClass
    public static void setupBeforeTests() throws Exception {
        stack = new MyStack();
    }

    /** Tests isEmpty() method */
    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack = stack.push(1);
        assertFalse(stack.isEmpty());
        stack = stack.pop();
        assertTrue(stack.isEmpty());
    }

    /** Tests top() method */
    @Test
    public void testTop() {
        stack = stack.push(1);
        assertEquals(1, stack.top());
        stack = stack.push(2);
        assertEquals(1, stack.top());
    }

    /** Tests push() and pop() methods */
    @Test
    public void testPushPop() {
        IStack s1 = new MyStack();
        s1 = s1.push(1);
        s1 = s1.push(2);
        s1 = s1.push(3);
        s1 = s1.push(4);
        s1 = s1.push(5);
        s1 = s1.pop();
        s1 = s1.pop();
        IStack s2 = new MyStack();
        s2 = s2.push(1);
        s2 = s2.push(2);
        s2 = s2.push(3);
        assertTrue(s1.equals(s2));
    }

    /** Tests LIFO property of queue */
    @Test
    public void testLIFO() {
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(5, 4, 3, 2, 1));
        List<Integer> actual = new ArrayList<Integer>();
        for(int i = 1; i <= 5; i++) {
            stack = stack.push(i);
        }
        while(!stack.isEmpty()) {
            actual.add(stack.top());
            stack = stack.pop();
        }
        assertTrue(actual.equals(expected));
    }
}