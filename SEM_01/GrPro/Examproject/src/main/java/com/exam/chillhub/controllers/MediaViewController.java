package com.exam.chillhub.controllers;

import com.exam.chillhub.database.MediaDB;
import com.exam.chillhub.enums.CategoryType;
import com.exam.chillhub.enums.MediaType;
import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.Season;
import com.exam.chillhub.models.Series;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class MediaViewController extends MediaController {
    @FXML
    private Label title;
    @FXML
    private Label year;
    @FXML
    private Label rating;
    @FXML
    private Accordion seasonsPane;
    @FXML
    private VBox contentBox;

    public void initialize() {
        super.initialize();
    }

    public void setModel(Series model) {
        // Overloaded function if and only if it is a series (used to load seasons and episodes)
        List<Season> seasons = model.getSeasons();
        for (Season season : seasons) {
            TitledPane tPane = new TitledPane();
            tPane.textProperty().bindBidirectional(season.getTitle());
            VBox vbox = new VBox();
            for (int i = 1; i <= season.getEpisodes(); i++) {
                HBox hbox = new HBox();
                Button playBtn = new Button("Play");
                playBtn.setOnAction(event -> super.onPlay());
                playBtn.getStyleClass().add("ui-transparent");
                hbox.getChildren().addAll(new Label("Episode " + i), playBtn);
                vbox.getChildren().add(hbox);
            }
            tPane.setContent(vbox);
            tPane.getStyleClass().add("ui-transparent");
            seasonsPane.getPanes().add(tPane);
            seasonsPane.getStyleClass().add("ui-transparent");
        }
    }

    public void setModel(Media model) {
        if (this.model != null) {
            title.textProperty().unbindBidirectional(this.model.titleProperty());
            year.textProperty().unbindBidirectional(this.model.yearProperty());
            rating.textProperty().unbindBidirectional(this.model.ratingProperty());

        }
        if (model.getType() == MediaType.SERIES) {
            setModel((Series) model);
        }
        super.setModel(model);
        title.textProperty().bindBidirectional(model.titleProperty());
        year.textProperty().bindBidirectional(model.yearProperty());
        rating.textProperty().bind(model.ratingProperty().asString());

        var categories = MediaDB.getInstance().getCategories();
        var mediaCategories = model.getCategories();
        for (CategoryType cat : mediaCategories) {
            var loaded = View.Category.load();
            contentBox.getChildren().add(loaded.node());
            Navigator controller = loaded.loader().getController();
            controller.onNavigateTo(navigable, categories.get(cat));
        }
    }

    @FXML
    public void onBack() {
        navigable.navigateBack();
    }
}
