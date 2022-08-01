/*
java -jar C:\projects\job4j_design\out\artifacts\job4j_design.jar -path=C:\projects\job4j_design\source.csv -delimiter=";" -out=C:\projects\job4j_design\target.csv -filter=name,age
 */
package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CSVReader {

    public static void validate(String source, String target, String delimiter, String filter) {
        if (source == null || target == null || delimiter == null || filter == null
                || source.isEmpty() || target.isEmpty() || delimiter.isEmpty() || filter.isEmpty()) {
            throw new IllegalArgumentException("введено некорректное количество аргументов: %d");
        }

        Path path = Paths.get(source);
        if (!Files.exists(path) || !path.toFile().isFile()) {
            throw new IllegalArgumentException(String.format("введен неправильное название файла ввода: %s", source));
        }
    }

    public static void handle(ArgsName args) {
        String source = args.get("path");
        String target = args.get("out");
        String delimiter = args.get("delimiter");
        String filter = args.get("filter");
        validate(source, target, delimiter, filter);

        String[] filters = filter.split(",");
        Integer[] numbers = new Integer[filters.length];

        try (FileInputStream input = new FileInputStream(source)) {

            try (OutputStream output = (target.equals("stdout")
                    ? new PrintStream(System.out) : new FileOutputStream(target))) {

                try (var scanner = new Scanner(input)) {
                    String[] str = scanner.nextLine().split(delimiter);
                    for (int f = 0; f < filters.length; f++) {
                        for (int s = 0; s < str.length; s++) {
                            if (filters[f].equals(str[s])) {
                                output.write(str[s].getBytes());
                                if (f != filters.length - 1) {
                                    output.write(";".getBytes());
                                }
                                numbers[f] = s;
                            }
                        }
                    }
                    output.write(System.lineSeparator().getBytes());

                    while (scanner.hasNextLine()) {
                        str = scanner.nextLine().split(delimiter);
                        for (int n = 0; n < numbers.length; n++) {
                            int i = numbers[n];
                            output.write(str[i].getBytes());
                            if (n != numbers.length - 1) {
                                output.write(";".getBytes());
                            }
                        }
                        output.write(System.lineSeparator().getBytes());
                    }

                }

            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName test = ArgsName.of(args);
        handle(test);
    }
}