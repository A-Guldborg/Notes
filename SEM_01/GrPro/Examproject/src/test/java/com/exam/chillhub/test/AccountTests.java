package com.exam.chillhub.test;

import com.exam.chillhub.models.Account;
import com.exam.chillhub.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTests {
    Account acc;

    @BeforeEach
    public void setUp() {
        acc = new Account("Test", "tseT");
    }

    @Test
    public void ctor() {
        assertEquals(acc.getUsername(), "Test");
        assertEquals(acc.getPassword(), "tseT");
        assertEquals(acc.getUsers().size(), 0);
    }

    @Test
    public void checkPassword() {
        assertTrue(acc.checkPassword("tseT"));
        assertFalse(acc.checkPassword("Wrong"));
        assertFalse(acc.checkPassword(""));
    }

    @Test
    public void addUser() {
        var user = new User("");
        acc.addUser(user);
        assertTrue(acc.getUsers().contains(user));
    }

    @Test
    public void deleteUser() {
        var user = new User("");
        acc.addUser(user);
        acc.deleteUser(user);
        assertEquals(acc.getUsers().size(), 0);
    }
}
