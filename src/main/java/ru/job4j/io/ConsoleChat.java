package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> answers = readPhrases();
        String question = null;
        List<String> dialog = new ArrayList<>();
        boolean flag = true;

        while (!OUT.equals(question)) {
            System.out.print("ВАШ ВОПРОС: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            question = reader.readLine();
            dialog.add("ВАШ ВОПРОС: " + question + System.lineSeparator());

            if (STOP.equals(question)) {
                flag = false;
            }
            if (CONTINUE.equals(question)) {
                flag = true;
            }
            if (flag) {
                int i = new Random().nextInt(answers.size());
                String answer = "ОТВЕТ БОТА: " + answers.get(i) + System.lineSeparator();
                System.out.println(answer);
                dialog.add(answer + System.lineSeparator());
            }

        }
        saveLog(dialog);
    }

    private List<String> readPhrases() {
        List<String> answers = null;
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            answers = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path)))) {
            log.forEach(out::print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./resources/consoleChat/path.txt",
                "./resources/consoleChat/botAnswers.txt");
        try {
            cc.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}