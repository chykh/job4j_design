package ru.job4j.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UsageSet {
    public static void main(String[] args) {
        /*
        Set<String> strings = Set.of("one", "two", "three");

         */
        Set<String> strings = new HashSet<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        strings.stream().filter(s -> s.length() < 5)
                .forEach(System.out::println);
        /*
        strings.add("two");
        int size = strings.size();
        System.out.println(size);
        boolean b = strings.contains("two");
        System.out.println(b);
        strings.removeIf(s -> s.startsWith("t"));
        for (String s : strings) {
            System.out.println(s);
        }

         */
        /*
        strings.retainAll(List.of("one", "six"));
        for (String s : strings) {
            System.out.println(s);
        }

         */
        /*strings.removeAll(List.of("six", "two", "three"));
        for (String s : strings) {
            System.out.println(strings);
        }

         */
        /*
        strings.remove("two");
        for (String s : strings) {
            System.out.println(s);
        }

         */
        /*
        Iterator<String> it = strings.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        */
        /*
        strings.addAll(List.of("one", "four", "five"));
        for (String s : strings) {
            System.out.println("Tekushii " + s);

        }

         */
    }
}
