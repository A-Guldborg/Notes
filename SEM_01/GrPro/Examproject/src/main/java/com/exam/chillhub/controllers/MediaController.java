package com.exam.chillhub.controllers;

import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.Model;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import static com.exam.chillhub.ChillhubApplication.openResource;

public class MediaController implements Navigator {
    @FXML
    protected ImageView poster;
    @FXML
    protected Button playBtn;
    @FXML
    protected Button favoriteBtn;

    protected Navigable navigable;
    protected Media model;
    private ChangeListener<Boolean> favoriteListener;
    private ImageView checkImg;
    private ImageView plusImg;

    public void initialize() {
        var img = new Image(openResource("play-circle-solid.png"));
        var imgView = new ImageView(img);
        playBtn.setGraphic(imgView);
        checkImg = new ImageView(new Image(openResource("check-circle-solid.png")));
        plusImg = new ImageView(new Image(openResource("plus-circle-solid.png")));

        favoriteListener = this::favoriteListenerMethod;
    }

    private void favoriteListenerMethod(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
        updateFavoriteBtn(newValue);
    }

    private void updateFavoriteBtn(boolean checked) {
        if (checked) {
            favoriteBtn.setGraphic(checkImg);
        } else {
            favoriteBtn.setGraphic(plusImg);
        }
    }

    public void setModel(Media model) {
        // Remove current listeners and bindings
        if (this.model != null) {
            this.model.favoriteProperty().removeListener(favoriteListener);
            poster.imageProperty().unbindBidirectional(this.model.posterProperty());
        }

        this.model = model;

        poster.imageProperty().bindBidirectional(model.posterProperty());
        model.favoriteProperty().addListener(favoriteListener);
        updateFavoriteBtn(model.getFavorite());
    }

    @FXML
    protected void onFavoriteAction() {
        // Set favorite to inverse of current value
        model.favoriteProperty().set(!model.getFavorite());
    }

    @Override
    public void onNavigateTo(Navigable navigable, Model model) {
        this.navigable = navigable;
        setModel((Media) model);
    }

    @FXML
    public void onClick() {
        navigable.navigateTo(View.MediaView, model);
    }

    @FXML
    public void onPlay() {
        var loaded = View.PlayerView.load();
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) loaded.node()));
        stage.show();
    }
}