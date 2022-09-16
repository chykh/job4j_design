package ru.job4j.ionio;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Visitor extends SimpleFileVisitor<Path> {
    private List<String> listResult = new ArrayList<>();

    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        String parent = file.getParent().toString();
        String fileName = file.toString();
        String result = fileName.replace(parent + "\\", "");
        listResult.add(result);
        return FileVisitResult.CONTINUE;
    }

    public List<String> getList() {
        return listResult;
    }
}