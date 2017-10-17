package edu.neu.ccs.cs5010.section2;

import java.util.*;

public interface IPriorityQueue<E> {
    void insert (E e);
    E remove();
    E front();
    boolean isEmpty();
    List<E> testForwardTraversal();
    List<E> testReverseTraversal();
    int getSize();
}
