package com.exam.chillhub.test;

import com.exam.chillhub.database.*;
import com.exam.chillhub.models.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AccountDBTests {
    private List<Account> accounts;

    @BeforeEach
    public void setUp() {
        accounts = AccountDB.getInstance().getAccounts();
    }

    @AfterEach
    public void save() {
        AccountDB.getInstance().saveAccounts();
    }

    @Test
    public void testAdminAccountLoads() {
        // Test is broken since we moved accounts.txt out of jar
        boolean bool = false;
        for (Account acc : accounts) {
            if (acc.getUsername().equals("admin")) {
                bool = true;
                assertEquals(acc.getUsers().size(), 2, "admin should have two users. Currently has " + acc.getUsers().size());
                List<User> users = acc.getUsers();
                assertEquals(users.get(0).getFavorites().getFilteredData().size(), 3);
                assertEquals(users.get(1).getFavorites().getFilteredData().size(), 5);
                break;
            }
        }

        assertTrue(bool, "admin account could not be found");
    }

    @Test
    public void testAddAndRemoveAccount() {
        Account account = new Account("user123", "strongpassword");
        AccountDB.getInstance().addAccount(account);
        AccountDB.getInstance().saveAccounts();
        List<Account> accounts = AccountDB.getInstance().getAccounts();
        boolean bool = false;
        for (Account acc : accounts) {
            if (acc.getUsername().equals("user123")) {
                assertEquals(acc.getPassword(), "strongpassword", "password should be strongpassword but found " + acc.getPassword() + " instead");
                bool = true;
                break;
            }
        }
        assertTrue(bool, "Test-user was not properly added to the database");
        // Removes the added account (such that the adding feature can be tested multiple times)
        AccountDB.getInstance().removeAccount(account);
    }
}
