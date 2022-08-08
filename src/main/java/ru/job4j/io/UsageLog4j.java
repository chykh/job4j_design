package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);

        int i = 15;
        char ch = 'q';
        byte b = 78;
        boolean flag = false;
        double number = 0.133;
        long l = 1000000L;
        float f = 0.000000001f;
        short s = 120;
        LOG.debug("Primitive types: int = {}, char = {}, byte = {}, boolean = {},"
                + " double = {}, long = {}, float = {}, short = {}", i, ch, b, flag, number, l, f, s);
    }

}