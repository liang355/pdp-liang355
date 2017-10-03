package edu.neu.ccs.cs5010;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import java.util.*;

public class QueueTest {
    private static IQueue queue = null;

    /** Tests isEmpty() method */
    @Test
    public void testIsEmpty() {
        queue = new MyQueue();
        assertTrue(queue.isEmpty());
        queue = queue.enqueue(1);
        assertFalse(queue.isEmpty());
        queue = queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    /** Tests front() method */
    @Test
    public void testFront() {
        queue = new MyQueue();
        queue = queue.enqueue(1);
        assertEquals(1, queue.front());
        queue = queue.enqueue(2);
        assertEquals(1, queue.front());
    }

    /** Tests enqueue() and dequeue() methods */
    @Test
    public void testEnqueueDequeue() {
        IQueue q1 = new MyQueue();
        q1 = q1.enqueue(1);
        q1 = q1.enqueue(2);
        q1 = q1.enqueue(3);
        q1 = q1.enqueue(4);
        q1 = q1.enqueue(5);
        q1 = q1.dequeue();
        q1 = q1.dequeue();
        IQueue q2 = new MyQueue();
        q2 = q2.enqueue(3);
        q2 = q2.enqueue(4);
        q2 = q2.enqueue(5);
        assertTrue(q1.equals(q2));
    }

    /** Tests FIFO property of queue */
    @Test
    public void testFIFO() {
        queue = new MyQueue();
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> actual = new ArrayList<Integer>();
        for(int i = 1; i <= 5; i++) {
            queue = queue.enqueue(i);
        }
        while(!queue.isEmpty()) {
            actual.add(queue.front());
            queue = queue.dequeue();
        }
        assertTrue(actual.equals(expected));
    }
}
