package ru.job4j.serialization.json;

public class Friend {
    private final boolean fat;
    private final int age;
    private final String name;

    public Friend(boolean fat, int age, String name) {
        this.fat = fat;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Friend{" + "fat=" + fat + ", age=" + age
                + ", name='" + name + '\'' + '}';
    }
}
