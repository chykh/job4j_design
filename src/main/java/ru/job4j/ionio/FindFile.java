package ru.job4j.ionio;

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

    public static void validate(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    String.format("введено некорректное количество аргументов: %d", args.length));
        }

        Path path = Paths.get(args[0]);
        if (!Files.exists(path) || !path.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("введен неправильный путь: %s", args[0]));
        }

        if (!("name".equals(args[2]) || "mask".equals(args[2]) || "regex".equals(args[2]))) {
            throw new IllegalArgumentException(String.format("введен неправильный фильтр: %s", args[2]));
        }
    }

    public static void main(String[] args) throws IOException {
        String input = args[0].replace("-d=", "");
        String filter = args[1].replace("-n=", "");
        String filterType = args[2].replace("-t=", "");
        String output = args[3].replace("-o=", "");
        List<String> result = new ArrayList<>();

        validate(args);
        Path start = Paths.get(input);
        Visitor visitor = new Visitor();
        Files.walkFileTree(start, visitor);
        List<String> files = visitor.getList();

        if ("name".equals(filterType)) {
            for (String file : files) {
                if (filter.equals(file)) {
                    result.add(file);
                }
            }
        }

        if ("mask".equals(filterType)) {
            filter = filter.replace("*", "\\w*");
            filter = filter.replace("?", "\\w");
            result = regex(filter, files);
        }

        if ("regex".equals(filterType)) {
            result = regex(filter, files);
        }

        PrintWriter out = new PrintWriter(output);
        out.print(result);
        out.close();
        System.out.println("see: " + args[3]);
    }
}
