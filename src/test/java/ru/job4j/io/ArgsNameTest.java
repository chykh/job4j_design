package ru.job4j.io;

import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;

public class ArgsNameTest {

    @Test
    public void whenCorrect(){
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        String expected = "UTF-8";
        String result = jvm.get("encoding");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void whenVoid() throws IllegalArgumentException{
        String message = "wrong arguments";

        try {
            String[] string = new String[] {""};
            ArgsName.of(string);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void whenNoKey() throws IllegalArgumentException{
        String message = "wrong arguments";

        try {
            String[] string = new String[] {"-=512"};
            ArgsName.of(string);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void whenNoSecondSymbol() throws IllegalArgumentException{
        String message = "wrong arguments";

        try {
            String[] string = new String[] {"-Xmx512"};
            ArgsName.of(string);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void whenNoFirstSymbol() throws IllegalArgumentException{
        String message = "wrong arguments";

        try {
            String[] string = new String[] {"Xmx=512"};
            ArgsName.of(string);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(message, e.getMessage());
        }
    }

}