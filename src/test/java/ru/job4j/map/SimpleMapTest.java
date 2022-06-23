package ru.job4j.map;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleMapTest extends TestCase {

    @Test
    public void testPutTrue() {
        Map<Integer, Character> simpleMap = new SimpleMap<Integer, Character>();
        simpleMap.put(1, 'q');
        simpleMap.put(4, 'w');
        simpleMap.put(8, 'e');
        simpleMap.put(3, 'r');
        simpleMap.put(-5, 't');

        assertTrue(simpleMap.put(14, 'y'));
    }

    @Test
    public void testPutFalse() {
        Map<Integer, Character> simpleMap = new SimpleMap<Integer, Character>();
        simpleMap.put(1, 'q');
        simpleMap.put(4, 'w');
        simpleMap.put(8, 'e');
        simpleMap.put(3, 'r');
        simpleMap.put(-5, 't');
        simpleMap.put(14, 'y');

        assertFalse(simpleMap.put(14, 'y'));
    }

    public void testGetTrue() {
        Map<Integer, Character> simpleMap = new SimpleMap<Integer, Character>();
        simpleMap.put(1, 'q');
        simpleMap.put(4, 'w');
        simpleMap.put(8, 'e');
        simpleMap.put(3, 'r');
        simpleMap.put(-5, 't');

        assertEquals('w', (char) simpleMap.get(4));
    }

    public void testGetFalse() {
        Map<Integer, Character> simpleMap = new SimpleMap<Integer, Character>();
        simpleMap.put(1, 'q');
        simpleMap.put(4, 'w');
        simpleMap.put(8, 'e');
        simpleMap.put(3, 'r');
        simpleMap.put(-5, 't');

        assertNotEquals('m', (char) simpleMap.get(4));
    }

    public void testRemoveTrue() {
        Map<Integer, Character> simpleMap = new SimpleMap<Integer, Character>();
        simpleMap.put(1, 'q');
        simpleMap.put(4, 'w');
        simpleMap.put(8, 'e');
        simpleMap.put(3, 'r');
        simpleMap.put(-5, 't');

        assertTrue(simpleMap.remove(8));
    }

    public void testRemoveFalse() {
        Map<Integer, Character> simpleMap = new SimpleMap<Integer, Character>();
        simpleMap.put(1, 'q');
        simpleMap.put(4, 'w');
        simpleMap.put(8, 'e');
        simpleMap.put(3, 'r');
        simpleMap.put(-5, 't');

        assertFalse(simpleMap.remove(14));
    }

    @Test
    public void testIterator() {
        Map<Integer, Character> simpleMap = new SimpleMap<Integer, Character>();
        simpleMap.put(1, 'q');
        simpleMap.put(4, 'w');
        simpleMap.put(8, 'e');
        simpleMap.put(3, 'r');
        simpleMap.put(-5, 't');
        Iterator iterator = simpleMap.iterator();
        Assert.assertTrue(iterator.hasNext());
    }

}