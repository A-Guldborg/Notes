package com.exam.chillhub.controllers;

import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.Filter;
import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.Model;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;


public class FilterViewController implements Navigator {
    @FXML
    private Label title;
    @FXML
    private Pane media;

    private Navigable navigable;
    private Filter model;
    private Map<Media, Node> children;

    public void setModel(Filter model) {
        // Unbind current properties
        if (this.model != null) {
            title.textProperty().unbindBidirectional(this.model.titleProperty());
        }

        this.model = model;
        children = new HashMap<>();

        // Bind new properties
        this.title.textProperty().bindBidirectional(model.titleProperty());

        // Load all media
        for (Media m : model.getFilteredData()) {
            add(m);
        }
    }

    public void add(Media media) {
        var loaded = View.Media.load();
        this.media.getChildren().add(loaded.node());
        Navigator controller = loaded.loader().getController();
        controller.onNavigateTo(navigable, media);
        children.put(media, loaded.node());
    }

    public void remove(Media media) {
        if (children.containsKey(media)) {
            this.media.getChildren().remove(children.get(media));
        }
    }

    @Override
    public void onNavigateTo(Navigable navigable, Model model) {
        this.navigable = navigable;
        setModel((Filter) model);
    }
}
