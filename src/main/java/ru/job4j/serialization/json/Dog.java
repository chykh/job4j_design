package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

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

        final Gson gsonDog = new GsonBuilder().create();
        String jsonString = gsonDog.toJson(dog);
        System.out.println("dog -> jsonString \n" + jsonString);

        JSONObject jsonDog = new JSONObject(jsonString);
        System.out.println("dog -> jsonObject \n" + jsonDog);
        System.out.println();

        final Gson gsonFriend = new GsonBuilder().create();
        jsonString = gsonFriend.toJson(friend);
        System.out.println("friend -> jsonString \n" + jsonString);

        JSONObject jsonFriend = new JSONObject(jsonString);
        System.out.println("friend -> jsonObject \n" + jsonFriend);
    }

}
