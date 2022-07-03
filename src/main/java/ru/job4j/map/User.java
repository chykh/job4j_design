package ru.job4j.map;

import java.util.*;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       User user = (User) o;
      return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    public static void main(String[] args) {
        User first = new User("Aleks", 3, new GregorianCalendar(1291, 01, 01));
        User second = new User("Aleks", 3, new GregorianCalendar(1291, 01, 01));
        int tableLength = 16;

        int hash1 = first.hashCode() ^ (first.hashCode() >>> 16);
        System.out.println("hash1 = " + hash1);
        int i1 = (tableLength - 1) & hash1;
        System.out.println("i1 = " + i1);

        int hash2 = second.hashCode() ^ (second.hashCode() >>> 16);
        System.out.println("hash2 = " + hash2);
        int i2 = (tableLength - 1) & hash2;
        System.out.println("i2 = " + i2);

    }
}
