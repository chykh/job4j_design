package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Programmer"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleNameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        store.add(new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Programmer"));
    }

    @Test
    public void whenReplaceThenRoleNameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        store.replace("1", new Role("1", "TaxiDriver"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("TaxiDriver"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        store.replace("10", new Role("10", "TaxiDriver"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Programmer"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Programmer"));
    }

}