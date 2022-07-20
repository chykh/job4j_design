package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        int i = 0;
        String question;
        while (i != 5) {
            i++;
            System.out.println(i + " Введите фразу: ");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                question = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
