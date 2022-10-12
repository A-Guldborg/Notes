package com.exam.chillhub.controllers;

import com.exam.chillhub.database.MediaDB;
import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.Filter;
import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.Model;
import com.exam.chillhub.models.User;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class HomeViewController implements Navigator {
    @FXML
    private VBox categories;

    private Navigable navigable;
    private User model;
    private FilterViewController favorites;
    private ListChangeListener<? super Media> listChangeListener;

    public void setModel(User model) {
        if (this.model != null) {
            model.getFavorites().getFilteredData().removeListener(listChangeListener);
        }

        this.model = model;
        categories.getChildren().clear();
        favorites = addCategory(model.getFavorites());
        for (var filter : MediaDB.getInstance().getCategories().values()) {
            addCategory(filter);
        }

        listChangeListener = (change -> {
            while (change.next()) {
                for (var media : change.getAddedSubList()) {
                    favorites.add(media);
                }
                for (var media : change.getRemoved()) {
                    favorites.remove(media);
                }
            }
        });

        model.getFavorites().getFilteredData().addListener(listChangeListener);
    }

    public FilterViewController addCategory(Filter filter) {
        var loaded = View.Category.load();
        categories.getChildren().add(loaded.node());
        FilterViewController controller = loaded.loader().getController();
        controller.onNavigateTo(navigable, filter);
        return controller;
    }

    @Override
    public void onNavigateTo(Navigable navigable, Model model) {
        this.navigable = navigable;
        setModel((User) model);
    }
}