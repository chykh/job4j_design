package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getList();
    }

    public static boolean validate(String[] args) {
        if (args[0].isEmpty() || !args[0].contains(".")) {
            throw new IllegalArgumentException("wrong first argument");
        }
        if (args[1].isEmpty() || args[1].length() > 4) {
            throw new IllegalArgumentException("wrong second argument");
        }
        return args.length == 2;
    }

    public static void main(String[] args) throws IOException {
        if (!validate(args)) {
            throw new IllegalArgumentException();
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

}