package ru.job4j.list;

import java.util.*;

public class ListUsage {
    public static void main(String[] args) {
        List<String> rsl = new ArrayList<>();
        rsl.add("one");
        rsl.add("two");
        rsl.add("three");
        rsl.sort(Comparator.reverseOrder());
       /* List<String> list = rsl.subList(1, 2);
        rsl = list;

        int i = rsl.lastIndexOf("qwqw");
        System.out.println(i);
         int i = rsl.indexOf("two");
        System.out.println(i);
         boolean b = rsl.contains("two");
        System.out.println(b);
        rsl.removeIf(s -> s.length() <= 3);
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("three");
        rsl.retainAll(list);
        rsl.remove("two");
        rsl.remove(1);
        rsl.replaceAll(String::toUpperCase);
        rsl.set(1, "two and second");

         */
        ListIterator<String> iterator = rsl.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
       /* for (int i = 0; i < rsl.size(); i++) {
            System.out.println(rsl.get(i));
        }
               rsl.add(1, "four");
        List<String> list = new ArrayList<>();
        list.add("four");
        list.add("five");
        rsl.addAll(2, list);

        for (String s : rsl) {
            System.out.println(s);
        }*/
    }
}
