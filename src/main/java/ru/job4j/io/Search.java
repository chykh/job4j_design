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

    public static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    String.format("введено некорректное количество аргументов: %d", args.length));
        }
        Path path = Paths.get(args[0]);
        if (!Files.exists(path) || !path.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("введен неправильный путь: %s", args[0]));
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException(String.format("введено неправильное расширение: %s", args[1]));
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }
}