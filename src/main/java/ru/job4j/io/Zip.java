package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static void validate(String[] args, Path folder) {
        if (args.length != 3) {
            throw new IllegalArgumentException(String.format("wrong arguments amount: %d", args.length));
        }

        if (!folder.toFile().exists()) {
            throw new IllegalArgumentException(String.format("wrong source folder: %s", folder));
        }

        if (!folder.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", folder));
        }
    }

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     }

    public static void main(String[] args) throws IOException {

        ArgsName arguments = ArgsName.of(args);
        Path folder = Path.of(arguments.get("d"));
        validate(args, folder);
        String unwanted = arguments.get("e");
        Path result = Path.of(arguments.get("o"));
        List<Path> listFiles = Search.search(folder, c -> !c.toString().contains(unwanted));
        Zip zip = new Zip();
        zip.packFiles(listFiles, result);
    }
}