package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    HashMap<FileProperty, List<Path>> hm = new HashMap<>();
    FileProperty fp;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        fp = new FileProperty(attrs.size(), file.getFileName().toString());
        hm.putIfAbsent(fp, new ArrayList<>());
        hm.get(fp).add(file);
        return super.visitFile(file, attrs);
    }

    public void show() {
        Set<FileProperty> set = hm.keySet();
        for (FileProperty fp : set) {
            if (hm.get(fp).size() > 1) {
                System.out.print("name = " + fp.getName());
                System.out.println("; size = " + fp.getSize());
                List<Path> list = hm.get(fp);
                list.forEach(System.out::println);
            }
        }
    }

}