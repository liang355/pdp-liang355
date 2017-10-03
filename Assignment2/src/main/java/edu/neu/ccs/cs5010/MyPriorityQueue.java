package edu.neu.ccs.cs5010;

import java.util.*;

public class MyPriorityQueue<E extends Comparable<E>> implements IPriorityQueue<E> {
    private List<E> items;

    public MyPriorityQueue() {
        items = new ArrayList<>();
    }

    private void floatUp() {
        int k = items.size() - 1;
        while (k > 0) {
            int p = (k - 1) / 2;
            E item = items.get(k);
            E parent = items.get(p);
            if (item.compareTo(parent) < 0) {
                // swap
                items.set(k, parent);
                items.set(p, item);
                // move up one level
                k = p;
            } else {
                break;
            }
        }
    }

    private void floatDown() {
        int k = 0;
        int l = 2 * k + 1;
        while (l < items.size()) {
            int min = l, r = l + 1;
            if (r < items.size()) { // there is a right child
                if (items.get(r).compareTo(items.get(l)) < 0) {
                    min++;
                }
            }
            if (items.get(k).compareTo(items.get(min)) > 0) {
                // switch
                E temp = items.get(k);
                items.set(k, items.get(min));
                items.set(min, temp);
                k = min;
                l = 2 * k + 1;
            } else {
                break;
            }
        }
    }

    public void insert(E e) {
        items.add(e);
        floatUp();
    }

    public E remove() throws NoSuchElementException {
        if (items.size() == 0) {
            throw new NoSuchElementException();
        }
        if (items.size() == 1) {
            return items.remove(0);
        }
        E hold = items.get(0);
        items.set(0, items.remove(items.size() - 1));
        floatDown();
        return hold;
    }

    public E front() {
        return items.get(0);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List testForwardTraversal() {
        return items;
    }

    public List testReverseTraversal() {
        List<E> reversedList = new ArrayList<>();
        for(E e : items) {
            reversedList.add(0, e);
        }
        return reversedList;
    }

    public int getSize() {
        return items.size();
    }

    public static void main(String[] args) {
        /* Testing the dynamics of MyPriorityQueue with scanner:
        1. enter integers, each followed by a Enter keystroke, the program will output the current structure of priority queue
        2. enter "done" when finished entering integers into the priority queue
        3. the program will output */

        MyPriorityQueue<Integer> q = new MyPriorityQueue<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter next int, 'done' to stop: ");
        String line = sc.next();
        while (!line.equals("done")) {
            q.insert(Integer.parseInt(line));
            System.out.println(q.testForwardTraversal());
            System.out.print("Enter next int, 'done' to stop: ");
            line = sc.next();
        }

        while (!q.isEmpty()) {
            int max = q.remove();
            System.out.println(max + " " + q.testForwardTraversal());
        }
    }

}
