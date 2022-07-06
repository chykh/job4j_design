package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            StringBuilder text = new StringBuilder();

            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] numbers = text.toString().split(System.lineSeparator());
            for (String number : numbers) {
                boolean valid = (Integer.parseInt(number) % 2) == 0;
                System.out.println(number + " is even? " + valid);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
