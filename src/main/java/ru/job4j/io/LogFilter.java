package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> result = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            List<String> list = in.lines().collect(Collectors.toList());

            for (String string : list) {
                String[] str = string.split(" ");
                if (str[str.length - 2].equals("404")) {
                    result.add(string);
                    result.add(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
