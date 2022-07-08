package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String[] array = read.lines().toArray(String[]::new);

            for (String string:array) {
                if (string.isBlank() || string.contains("#")) {
                    continue;
                }

                String[] str = string.split("=", 2);

                try {
                     if (str.length != 2 || str[0].isBlank() || str[1].isBlank()) {
                          throw new IllegalArgumentException(str[0] + str[1]);
                     }
                     values.put(str[0], str[1]);
                } catch (IllegalArgumentException e) {
                    System.out.println("Выявлена строка с нарушением формата: " + e.getMessage());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("app.properties");
        System.out.println(config);
        config.load();
        System.out.println(config.value("hibernate.connection.username"));

    }

}
