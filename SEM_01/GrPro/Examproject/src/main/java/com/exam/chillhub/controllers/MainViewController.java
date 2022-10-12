package com.exam.chillhub.controllers;

import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.Model;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.util.Stack;

public class MainViewController implements Navigable {
    private final Stack<NavigationFrame> navigationStack = new Stack<>();
    @FXML
    private AnchorPane pane;
    private NavigationFrame navigationTop;

    @FXML
    public void initialize() {
        navigateTo(View.LoginView, null);
    }

    private void setView(NavigationFrame frame) {
        var loaded = frame.view().load();
        var node = loaded.node();

        pane.getChildren().clear();
        pane.getChildren().add(node);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);

        Navigator navigator = loaded.loader().getController();
        navigator.onNavigateTo(this, frame.model());
    }

    @Override
    public void navigateTo(View view, Model model) {
        navigationStack.add(navigationTop);
        navigationTop = new NavigationFrame(view, model);
        setView(navigationTop);
    }

    @Override
    public void navigateBack() {
        if (navigationStack.isEmpty())
            return;
        navigationTop = navigationStack.pop();
        setView(navigationTop);
    }
}
