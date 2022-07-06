package ru.job4j.io;

import java.io.*;
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
                if ("404".equals(str[str.length - 2])) {
                    result.add(string);
                    result.add(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(file))
        )) {
            out.println(log);
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
        save(log, "404.txt");
    }
}
