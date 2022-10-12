package com.exam.chillhub.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Season extends Model {
    private final int number;
    private final int episodes;
    private final StringProperty title;

    public Season(int number, int episodes) {
        if (episodes < 0)
            throw new IllegalArgumentException("episodes");
        this.number = number;
        this.episodes = episodes;
        title = new SimpleStringProperty("Season " + number);
    }

    public int getSeasonNumber() {
        return this.number;
    }

    public int getEpisodes() {
        return this.episodes;
    }

    public StringProperty getTitle() {
        return this.title;
    }
}
