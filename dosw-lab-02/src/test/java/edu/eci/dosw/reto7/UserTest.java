package edu.eci.dosw.reto7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void shouldStoreIdGivenInConstructor() {
        User user = new User("0001", "mom");

        assertEquals("0001", user.getID());
    }

    @Test
    void shouldStoreNameGivenInConstructor() {
        User user = new User("0001", "mom");

        assertEquals("mom", user.getName());
    }

    @Test
    void shouldKeepIndependentDataForDifferentUsers() {
        User mom = new User("0001", "mom");
        User dad = new User("0002", "dad");

        assertEquals("0001", mom.getID());
        assertEquals("0002", dad.getID());
        assertEquals("mom", mom.getName());
        assertEquals("dad", dad.getName());
    }
}
