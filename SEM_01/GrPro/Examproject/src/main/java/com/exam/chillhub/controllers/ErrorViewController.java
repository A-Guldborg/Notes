package com.exam.chillhub.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ErrorViewController {
    @FXML
    private Label title;
    @FXML
    private Label message;
    @FXML
    private TextArea error;

    public void setTitle(String title) {
        this.title.textProperty().set(title);
    }

    public void setMessage(String message) {
        this.message.textProperty().set(message);
    }

    public void setError(String error) {
        this.error.textProperty().set(error);
    }
}
