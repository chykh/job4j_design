package ru.job4j.io;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class AnalizyTest{
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenSourceThenTarget() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("100 00:15:01");
            out.println("500 02:46:01");
            out.println("400 04:21:01");
            out.println("800 06:51:01");
            out.println("400 10:26:01");
        }

        Analizy an = new Analizy();
        an.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader result = new BufferedReader(new FileReader(target))) {
             result.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("02:46:01;06:51:0110:26:01;"));

    }
}