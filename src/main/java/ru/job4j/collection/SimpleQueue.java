package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    int pushCounter;
    private final SimpleStack<T> out = new SimpleStack<>();
    int pollCounter;

    public T poll() {
        if (pollCounter == 0) {
            if (pushCounter == 0) {
                throw new NoSuchElementException();
            }
            while (pushCounter > 0) {
                out.push(in.pop());
                pushCounter--;
                pollCounter++;
            }
        }
        pollCounter--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        pushCounter++;
    }

}

