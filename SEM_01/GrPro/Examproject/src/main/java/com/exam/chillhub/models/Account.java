package com.exam.chillhub.models;

import java.util.ArrayList;
import java.util.List;

public class Account extends Model {
    protected String username;
    protected String password;
    protected List<User> users;

    public Account(String username, String password) {
        this.password = password;
        this.username = username;
        users = new ArrayList<>();
    }

    public boolean checkPassword(String EnterYourPassword) {
        return EnterYourPassword.equals(password);
    }

    public void addUser(User u) {
        users.add(u);
    }

    public void deleteUser(User u) {
        users.remove(u);
    }

    public List<User> getUsers() {
        return users;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}


