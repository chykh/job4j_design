package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {

         try (BufferedReader in = new BufferedReader(new FileReader(source));
              PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
             List<String> list = in.lines().collect(Collectors.toList());
             boolean flag = false;

             for (String string : list) {
                 String[] str = string.split(" ");

                 if (("400".equals(str[0]) || "500".equals(str[0])) && !flag) {
                    flag = true;
                    out.print(str[1] + ";");
                    continue;
                 }

                 if (flag && !"400".equals(str[0]) && !"500".equals(str[0])) {
                    flag = false;
                    out.println(str[1]);
                 }
             }

         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    public static void main(String[] args) {
        Analizy first = new Analizy();
        String path = "./resources/analyzy/";
        first.unavailable(path + "Analyzy_source.txt", path +  "Analyzy_target.txt");

    }
}