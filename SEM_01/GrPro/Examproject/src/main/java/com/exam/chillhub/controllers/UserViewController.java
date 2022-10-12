package com.exam.chillhub.controllers;

import com.exam.chillhub.database.MediaDB;
import com.exam.chillhub.enums.CategoryType;
import com.exam.chillhub.enums.MediaType;
import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.Model;
import com.exam.chillhub.models.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class UserViewController implements Navigable, Navigator {
    private final HashMap<NavigationFrame, Node> viewCache = new HashMap<>();
    private final Stack<NavigationFrame> navigationStack = new Stack<>();
    @FXML
    private ComboBox<CategoryType> categoryPicker;
    @FXML
    private TextField searchInput;
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane mainPane;
    private User model;
    private MediaType type;
    private boolean categoryActive = true;
    private NavigationFrame navigationTop;
    private Navigable navigable;

    @FXML
    public void initialize() {
        type = MediaType.ANY;
        categoryPicker.setItems(FXCollections.observableArrayList(CategoryType.values()));
        resetCategory();
    }

    private void resetCategory() {
        categoryActive = false;
        categoryPicker.setValue(CategoryType.NONE);
        categoryActive = true;
    }

    @FXML
    private void homeAction() {
        type = MediaType.ANY;
        navigateTo(View.HomeView, model);
        resetCategory();
    }

    @FXML
    private void moviesAction() {
        type = MediaType.MOVIE;
        navigateTo(View.FilterView, MediaDB.getInstance().getDB().getFilteredType(type));
        resetCategory();
    }

    @FXML
    private void seriesAction() {
        type = MediaType.SERIES;
        navigateTo(View.FilterView, MediaDB.getInstance().getDB().getFilteredType(type));
        resetCategory();
    }

    @FXML
    public void categorySelect() {
        if (!categoryActive)
            return;

        if (categoryPicker.getValue() == CategoryType.NONE) {
            navigateTo(View.HomeView, model);
            return;
        }

        var filter = MediaDB.getInstance().getCategories().get(categoryPicker.getValue());

        if (type != MediaType.ANY)
            filter = filter.getFilteredType(type);

        navigateTo(View.FilterView, filter);
    }

    @FXML
    protected void searchAction() {
        var text = searchInput.textProperty().get();
        var filter = MediaDB.getInstance().getDB().search(text);
        navigateTo(View.FilterView, filter);
    }

    @FXML
    public void onUsersAction() {
        navigable.navigateBack();
    }

    private void setView(NavigationFrame frame) {
        Node node = viewCache.get(frame);

        if (node == null) {
            var loaded = frame.view().load();
            node = loaded.node();

            Navigator navigator = loaded.loader().getController();
            navigator.onNavigateTo(this, frame.model());

            viewCache.put(frame, loaded.node());
        }

        mainPane.getChildren().clear();
        mainPane.getChildren().add(node);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
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

    @Override
    public void onNavigateTo(Navigable navigable, Model model) {
        this.navigable = navigable;
        this.model = (User) model;
        this.model.getFavorites().resetFilteredData();

        borderPane.setStyle(this.model.getColor());

        // Set up listeners etc. for favorites list
        var favorites = new HashSet<>(this.model.getFavorites().getFilteredData());
        for (var media : MediaDB.getInstance().getDB().getFilteredData()) {
            media.resetFavorite();
            media.setFavorite(favorites.contains(media));
            media.favoriteProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue)
                    this.model.getFavorites().getFilteredData().add(media);
                else
                    this.model.getFavorites().getFilteredData().remove(media);
            });
        }

        navigateTo(View.HomeView, model);
    }
}
