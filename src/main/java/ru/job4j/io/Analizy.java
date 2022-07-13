package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
         List<String> result = new ArrayList<>();

         try (BufferedReader in = new BufferedReader(new FileReader(source))) {
             List<String> list = in.lines().collect(Collectors.toList());
             String res1 = null;
             String res2;
             boolean flag = false;

             for (String string : list) {
                 String[] str = string.split(" ");

                 if ((str[0].equals("400") || str[0].equals("500")) && !flag) {
                    res1 = str[1];
                    flag = true;
                    continue;
                 }

                 if (flag && !str[0].equals("400") && !str[0].equals("500")) {
                    res2 = res1 + ";" + str[1];
                    flag = false;
                    result.add(res2);
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }


         try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
             for ( String string:result) {
                   out.println(string);
             }

         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }

    }

    public static void main(String[] args) {
        Analizy first = new Analizy();
        String path = "./resources/analyzy/";
        first.unavailable(path + "Analyzy_source.txt",path +  "Analyzy_target.txt");

    }
}