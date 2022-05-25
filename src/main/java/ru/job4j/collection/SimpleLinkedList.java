package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size;
    private Node<E> last;
    private Node<E> first;
    private Node<E> current;
    private int modCount;

    private static class Node<E> {
        private final E element;
        private int counter;
        private Node<E> next;

        private Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        Node<E> prev = last;
        Node<E> current = new Node<>(value, null);
        last = current;
        if (prev == null) {
            first = current;
        } else {
            prev.next = current;
        }
        current.counter = size++;
        this.current = current;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        current = first;
        while (current.counter != index) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public Iterator<E> iterator() {
        current = first;
        return new Iterator<>() {
            private int step = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return step < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = current.element;
                current = current.next;
                step++;
                return rsl;
            }
        };

    }

}