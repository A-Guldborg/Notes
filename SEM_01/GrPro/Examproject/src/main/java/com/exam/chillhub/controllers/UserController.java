package com.exam.chillhub.controllers;
import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.Model;
import com.exam.chillhub.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.util.Random;

public class UserController implements Navigator {
    @FXML
    public TextField DefaultUsernameUser;
    @FXML
    public AnchorPane background;

    private Navigable navigable;

    String lg1 = "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,  #2193b0,  #6dd5ed);";
    String lg2 = "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,  #cc2b5e,  #753a88);";
    String lg3 = "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,  #56ab2f,  #a8e063);";
    String lg4 = "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,  #eacda3,  #d6ae7b);";
    String lg5 = "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,  #ba5370,  #f4e2d8);";
    String lg6 = "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,  #dd5e89,  #f7bb97);";

    private User model;

    public void setModel(User model) {
        this.model = model;
        background.setStyle(model.getColor());
        DefaultUsernameUser.textProperty().bindBidirectional(model.nameProperty());
    }

    @FXML
    public void changeColorAction(){
        model.generateColor();
        background.setStyle(model.getColor());
    }

    @FXML
    public void onClick() {
        navigable.navigateTo(View.UserView, model);
    }

    @Override
    public void onNavigateTo(Navigable navigable, Model model) {
        this.navigable = navigable;
        setModel((User) model);
    }
}


