package com.exam.chillhub.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static com.exam.chillhub.ChillhubApplication.getResource;

public class PlayerViewController {
    private static final Media media;

    static {
        try {
            var videoPath = getResource("video.mp4").toURI().toURL().toString();
            media = new Media(videoPath);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @FXML
    private StackPane pane;
    private MediaPlayer player;

    @FXML
    public void initialize() {
        player = new MediaPlayer(media);
        MediaView view = new MediaView(player);
        view.fitHeightProperty().bind(Bindings.selectDouble(view.sceneProperty(), "height"));
        view.fitWidthProperty().bind(Bindings.selectDouble(view.sceneProperty(), "width"));
        view.setPreserveRatio(true);

        // Set up stopping the player when the stage is closed (messy :/)
        pane.sceneProperty().addListener((scene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                scene.getValue().windowProperty().addListener((stage, oldStage, newStage) -> {
                    if (oldStage == null && newStage != null) {
                        stage.getValue().setOnCloseRequest(event -> {
                            player.stop();
                            player.dispose();
                        });
                    }
                });
            }
        });
        pane.getChildren().add(view);

        player.play();
    }
}
