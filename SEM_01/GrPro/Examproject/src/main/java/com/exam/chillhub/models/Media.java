package com.exam.chillhub.models;

import com.exam.chillhub.enums.CategoryType;
import com.exam.chillhub.enums.MediaType;
import javafx.beans.property.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import static com.exam.chillhub.ChillhubApplication.openResource;

public abstract class Media extends Model {
    private final StringProperty name;
    private final MediaType type;
    private final DoubleProperty rating;
    private final StringProperty year;
    private final int idx;
    private final ObjectProperty<Image> poster;
    private final StringProperty titleProperty;
    private BooleanProperty favorite;
    private List<CategoryType> categories;

    public Media(MediaType type, String name, String year, double rating, boolean favorite, int idx) {
        this.name = new SimpleStringProperty(name);
        this.type = type;
        this.rating = new SimpleDoubleProperty(rating);
        this.year = new SimpleStringProperty(year);
        this.favorite = new SimpleBooleanProperty(favorite);
        this.idx = idx;
        this.titleProperty = new SimpleStringProperty(name + " (" + rating + ")");
        this.categories = new ArrayList<>();

        this.poster = new SimpleObjectProperty<>();
        {
            var filename = getType().name().toLowerCase() + "/posters/" + getName() + ".jpg";
            var res = openResource(filename);

            if (res != null) {
                poster.set(new Image(res));
            }
        }
    }

    public String getName() {
        return this.name.get();
    }

    public MediaType getType() {
        return this.type;
    }

    public double getRating() {
        return this.rating.get();
    }

    public String getYear() {
        return this.year.get();
    }

    public boolean getFavorite() {
        return this.favorite.get();
    }

    public void setFavorite(boolean favorite) {
        favoriteProperty().set(favorite);
    }

    public int getIdx() {
        return this.idx;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty ratingProperty() {
        return rating;
    }

    public StringProperty yearProperty() {
        return year;
    }

    public BooleanProperty favoriteProperty() {
        return favorite;
    }

    public ObjectProperty<Image> posterProperty() {
        return poster;
    }

    public StringProperty titleProperty() {
        return this.titleProperty;
    }

    public void addCategory(CategoryType c) {
        this.categories.add(c);
    }

    public List<CategoryType> getCategories() {
        return this.categories;
    }

    public void resetFavorite() {
        // Ugly "remove all listeners"
        favorite = new SimpleBooleanProperty(false);
    }
}
