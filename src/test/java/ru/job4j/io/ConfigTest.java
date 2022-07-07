package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenTwoValues() {
        String path = "./resources/config/whenTwoValues.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Aleksandr"));
        assertThat(config.value("surname"), is("Sergeev"));
    }

    @Test
    public void whenCommentsVoidStrings() {
        String path = "./resources/config/whenCommentsVoidStrings.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Aleksandr"));
        assertThat(config.value("surname"), is("Sergeev"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongTemplate1() {
        String path = "./resources/config/whenWrongTemplate1.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongTemplate2() {
        String path = "./resources/config/whenWrongTemplate2.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongTemplate3() {
        String path = "./resources/config/whenWrongTemplate3.properties";
        Config config = new Config(path);
        config.load();
    }
}