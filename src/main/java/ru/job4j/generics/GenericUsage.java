package ru.job4j.generics;

import java.util.*;

public class GenericUsage {
    public void printRsl(Collection<?> col) {
        for (Iterator<?> it = col.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println(next);
        }
    }

    public void printInfo(Collection<? extends Person> col) {
        for (Iterator<? extends Person> it = col.iterator(); it.hasNext();) {
            Person next = it.next();
            System.out.println(next);
        }
    }

    public static void main(String[] args) {
       List<Person> per = List.of(new Person("name", 21, new Date(913716000000L)));
       new GenericUsage().printInfo(per);
        System.out.println("____________________________________");
       List<Programmer> pro = List.of(new Programmer("name123", 23, new Date(913716000000L)));
       new GenericUsage().printInfo(pro);
    }
}
