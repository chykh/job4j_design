package ru.job4j.iterator;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddAfterFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 0, 5);
        assertThat(input, is(Arrays.asList(0, 5, 1, 2)));
    }

    @Test(expected = IndexOutOfBoundsException .class)
    public void whenAddAfterException() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 10, 5);
    }

    @Test
    public void whenRemoveFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        Predicate<Integer> predicate = x -> x < 1;
        ListUtils.removeIf(input, predicate);
        assertThat(input, is(Arrays.asList(1, 2)));
    }

    @Test
    public void whenRemoveLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        Predicate<Integer> predicate = x -> x == 2;
        ListUtils.removeIf(input, predicate);
        assertThat(input, is(Arrays.asList(0, 1)));
    }

    @Test
    public void whenNoRemove() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        Predicate<Integer> predicate = x -> x > 5;
        ListUtils.removeIf(input, predicate);
        assertThat(input, is(Arrays.asList(0, 1, 2)));
    }

    @Test
    public void whenReplaceFirstAndLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 6, 0));
        Predicate<Integer> predicate = x -> x == 0;
        ListUtils.replaceIf(input, predicate, 20);
        assertThat(input, is(Arrays.asList(20, 1, 2, 6, 20)));
    }

    @Test
    public void whenReplaceSeveral() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 6, 0, 2));
        Predicate<Integer> predicate = x -> x == 2;
        ListUtils.replaceIf(input, predicate, 777);
        assertThat(input, is(Arrays.asList(0, 1, 777, 6, 0, 777)));
    }

    @Test
    public void whenNoReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 6, 0, 2));
        Predicate<Integer> predicate = x -> x < 0;
        ListUtils.replaceIf(input, predicate, 12);
        assertThat(input, is(Arrays.asList(0, 1, 2, 6, 0, 2)));
    }

    @Test
    public void whenRemoveAllMiddle() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 6, 0));
        List<Integer> deleted = new ArrayList<>(Arrays.asList(1, 2, 6, 6));
        ListUtils.removeAll(input, deleted);
        assertThat(input, is(Arrays.asList(0, 0)));
    }

    @Test
    public void whenRemoveBeginning() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 2, 1, 2, 6, 0));
        List<Integer> deleted = new ArrayList<>(Arrays.asList(0, 2));
        ListUtils.removeAll(input, deleted);
        assertThat(input, is(Arrays.asList(1, 6)));
    }

    @Test
    public void whenRemoveNo() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 2, 1, 2, 6, 0));
        List<Integer> deleted = new ArrayList<>(Arrays.asList(77, 12));
        ListUtils.removeAll(input, deleted);
        assertThat(input, is(Arrays.asList(0, 2, 1, 2, 6, 0)));
    }

}