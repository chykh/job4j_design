package ru.job4j.ionio;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFile {
    public static List<String> regex(String filter, List<String> files) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(filter);
        for (String file : files) {
            Matcher matcher = pattern.matcher(file);
            while (matcher.find()) {
                result.add(file.substring(matcher.start(), matcher.end()));
            }
        }
        return result;
    }

    private static void validate(String input, String filterType) {
        Path path = Paths.get(input);
        if (!Files.exists(path) || !path.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("введен неправильный путь: %s", input));
        }

        if (!("name".equals(filterType) || "mask".equals(filterType) || "regex".equals(filterType))) {
            throw new IllegalArgumentException(String.format("введен неправильный фильтр: %s", filterType));
        }
    }

    private static List<String> choose(String filterType, List<String> files, String filter) {
        List<String> result = new ArrayList<>();

        if ("name".equals(filterType)) {
            for (String file : files) {
                if (filter.equals(file)) {
                    result.add(file);
                }
            }
        }

        if ("mask".equals(filterType)) {
            filter = filter.replace(".", "\\.");
            filter = filter.replace("*", "\\w*");
            filter = filter.replace("?", "\\w");
            result = regex(filter, files);
        }

        if ("regex".equals(filterType)) {
            result = regex(filter, files);
        }
        return result;
    }

    private static void write(List<String> result, String output) throws IOException {
        PrintWriter out = new PrintWriter(output);
        out.print(result);
        out.close();
        System.out.println("see: " + output);
    }

    public static void main(String[] args) throws IOException {
        List<Path> files;
        List<String> result = new ArrayList<>();

        if (args.length != 4) {
            throw new IllegalArgumentException(
                    String.format("введено некорректное количество аргументов: %d", args.length));
        }
        ArgsName argsName = ArgsName.of(args);
        String input = argsName.get("d");
        String filter = argsName.get("n");
        String filterType = argsName.get("t");
        String output = argsName.get("o");
        validate(input, filterType);

        Path start = Paths.get(input);
         files = Search.search(start, p -> p.toFile().isFile());
        for (Path path : files) {
            result.add(path.getFileName().toString());
        }

        result = choose(filterType, result, filter);
        write(result, output);
    }
}