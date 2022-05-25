package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private Node<E> lastAdded;
    private Node<E> first;
    private int modCount;
    private int size;

    private static class Node<E> {
        private final E element;
        private Node<E> next;

        private Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        Node<E> previous = lastAdded;
        Node<E> newNode = new Node<>(value, null);
        lastAdded = newNode;
        if (previous == null) {
            first = newNode;
        } else {
            previous.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = first;
        int counter = 0;
        while (counter++ != index) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<>() {
            Node<E> current = first;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = current.element;
                current = current.next;
                return rsl;
            }
        };

    }
}