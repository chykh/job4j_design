package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

/* чтение по байтам:

try (FileInputStream in = new FileInputStream("input.txt")) {
    StringBuilder text = new StringBuilder();
    int read;
    while ((read = in.read()) != -1) {
        text.append((char) read);
    }
    System.out.println(text);

    String[] lines = text.toString().split(System.lineSeparator());
    for (String line : lines) {
        System.out.println(line);
    }
} catch (Exception e) {
    e.printStackTrace();
}

чтение по оберткам:
 */

public class ReadFile {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            in.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
