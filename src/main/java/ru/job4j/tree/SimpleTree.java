package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Node<E> parentNode = null;
        Optional<Node<E>> optChild = findBy(child);
        Optional<Node<E>> optParent = findBy(parent);
        boolean valid = optParent.isPresent() && optChild.isEmpty();
        if (valid) {
            parentNode = optParent.get();
            parentNode.children.add(new Node<E>(child));
        }
       return valid;
    }

    @Override
    public Optional<Node<E>> findBy(E val) {
        return findByPredicate(m -> m.value.equals(val));
     }

    @Override
    public boolean isBinary() {
        Optional<Node<E>> opt = findByPredicate(m -> m.children.size() > 2);
        return opt.isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> opt = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                opt = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return opt;
    }
}