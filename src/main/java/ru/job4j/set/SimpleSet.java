package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(1);

    @Override
    public boolean add(T value) {
        boolean valid = !contains(value);
        if (valid) {
            set.add(value);
        }
        return valid;
    }

    @Override
    public boolean contains(T value) {
        boolean valid = false;

        for (T element : set) {
            valid = (value == null && element == null) || (element != null && element.equals(value));
            if (valid) {
                break;
            }
        }
        return valid;
    }

   @Override
   public Iterator<T> iterator() {
       return set.iterator();
   }
}