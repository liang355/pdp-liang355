package edu.neu.ccs.cs5010;

import java.util.*;

public interface IPriorityQueue<E> {
    void insert (E e);
    E remove();
    E front();
    boolean isEmpty();
    List testForwardTraversal();
    List testReverseTraversal();
}
