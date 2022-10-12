package com.exam.chillhub.controllers;

import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.Account;
import com.exam.chillhub.models.Model;
import com.exam.chillhub.models.User;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class AccountViewController implements Navigator {
    @FXML
    private HBox users;

    private Navigable navigable;
    private Account model;

    public void setModel(Account model) {
        this.model = model;

        for (var user : model.getUsers()) {
            addUser(user);
        }
    }

    private void addUser(User user) {
        var loaded = View.User.load();
        users.getChildren().add(loaded.node());
        Navigator controller = loaded.loader().getController();
        controller.onNavigateTo(navigable, user);
    }

    @FXML
    public void addUserAction() {
        var newUser = new User("New user");
        model.addUser(newUser);
        addUser(newUser);
    }

    @FXML
    public void deleteUserAction() {

    }

    @Override
    public void onNavigateTo(Navigable navigable, Model model) {
        this.navigable = navigable;
        setModel((Account) model);
    }

    @FXML
    public void onLogOut() {
        navigable.navigateBack();
    }
}




