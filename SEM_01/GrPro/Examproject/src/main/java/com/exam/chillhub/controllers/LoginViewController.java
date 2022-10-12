package com.exam.chillhub.controllers;

import com.exam.chillhub.database.AccountDB;
import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.Account;
import com.exam.chillhub.models.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController implements Navigator {
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    public Label WrongPasswordOrUsername;
    @FXML
    public Label UsernameAlreadyTaken;

    private Navigable navigable;

    @FXML
    public void loginAction() {
        UsernameAlreadyTaken.visibleProperty().setValue(false);
        WrongPasswordOrUsername.visibleProperty().setValue(false);

        boolean found = false;
        for (var acc : AccountDB.getInstance().getAccounts()) {
            if (acc.getUsername().equals(username.textProperty().get())) {
                found = true;
                if (acc.checkPassword(password.textProperty().get())) {
                    navigable.navigateTo(View.AccountView, acc);
                } else {
                    WrongPasswordOrUsername.visibleProperty().setValue(true);
                    break;
                }
            }
        }

        if (!found)
            WrongPasswordOrUsername.visibleProperty().setValue(true);
    }

    @FXML
    public void registerAction() {
        UsernameAlreadyTaken.visibleProperty().setValue(false);
        WrongPasswordOrUsername.visibleProperty().setValue(false);

        if (username != null && password != null) {
            var newAcc = new Account(username.textProperty().get(), password.textProperty().get());
            boolean successfulCreation = AccountDB.getInstance().addAccount(newAcc);
            if (successfulCreation) {
                navigable.navigateTo(View.AccountView, newAcc);
            } else {
                UsernameAlreadyTaken.visibleProperty().setValue(true);
            }
        }
    }

    @Override
    public void onNavigateTo(Navigable navigable, Model ignored) {
        this.navigable = navigable;
    }
}

