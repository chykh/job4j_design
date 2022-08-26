package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Dog {
    private final boolean fat;
    private final int age;
    private final String name;
    private final String[] meals;
    private final Friend friend;

    public Dog(boolean fat, int age, String name, String[] meals, Friend friend) {
        this.fat = fat;
        this.age = age;
        this.name = name;
        this.meals = meals;
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "Dog{" + "fat=" + fat + ", age=" + age + ", name='"
                + name + '\'' + ", meals=" + Arrays.toString(meals)
                + ", friend=" + friend + '}';
    }

    public static void main(String[] args) {
        final Friend friend = new Friend(true, 1, "Sharik");
        final Dog dog = new Dog(false, 1, "Kvadrat", new String[] {"meat", "milk", "sup"}, friend);
        final Gson gson = new GsonBuilder().create();
        final String gsonString = gson.toJson(dog);
        System.out.println("Строка gson: \n" + gsonString);

        final Dog gsonDog = gson.fromJson(gsonString, Dog.class);
        System.out.println("Объект из gson: \n" + gsonDog);
    }

}
