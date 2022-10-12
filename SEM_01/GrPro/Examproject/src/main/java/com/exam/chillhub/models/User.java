package com.exam.chillhub.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User extends Model {
    private StringProperty name;
    private Filter favorites;
    private String color;

    public User(String name) {
        this(name, getRandomColor());
    }

    public User(String name, String color) {
        if (Pattern.matches("#[\\w]{6}, #[\\w]{6}", color)) {
            this.color = color;
        } else {
            this.color = "#6d8c82, #a03f74";
        }
        this.name = new SimpleStringProperty(name);
        this.favorites = new Filter("Favorites");
    }

    private static String getRandomColor() {
        int diff = 100;
        Random random = new Random();
        int red = random.nextInt(256 - diff * 2) + diff;
        int green = random.nextInt(256 - diff * 2) + diff;
        int blue = random.nextInt(256 - diff * 2) + diff;
        String redString = Integer.toHexString(red);
        String greenString = Integer.toHexString(green);
        String blueString = Integer.toHexString(blue);
        if(redString.length() == 1){
            redString = "0" + redString;
        }
        if(greenString.length() == 1){
            greenString = "0" + greenString;
        }
        if(blueString.length() == 1){
            blueString = "0" + blueString;
        }
        String colorString = "#" + redString + greenString + blueString;

        int redDelta = red + random.nextInt(diff * 2) - diff;
        int greenDelta = green + random.nextInt(diff * 2) - diff;
        int blueDelta = blue + random.nextInt(diff * 2) - diff;
        String redDeltaString = Integer.toHexString(redDelta);
        String greenDeltaString  = Integer.toHexString(greenDelta);
        String blueDeltaString = Integer.toHexString(blueDelta);
        if(redDeltaString.length() == 1) {
            redDeltaString = "0" + redDeltaString;
        }
        if(greenDeltaString.length() == 1) {
            greenDeltaString = "0" + greenDeltaString;

        }
        if(blueDeltaString.length() == 1) {
            blueDeltaString = "0" + blueDeltaString;
        }
        String colorString2 = "#" + redDeltaString + greenDeltaString + blueDeltaString;
        return colorString + ", " + colorString2;
    }

    public void generateColor() {
        color = getRandomColor();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getColor() {
        return "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, " + this.color + ");";
    }

    public String getColorString() {
        return this.color;
    }

    public Filter getFavorites() {
        return favorites;
    }

    public void changeFavorite(Media media) {
        // Tjekker om det er muligt at fjerne media fra favorites, og hvis det ikke kan lade sig gøre tilføjer den i stedet
        if (!favorites.getFilteredData().remove(media)) {
            favorites.addToFilter(media);
        }
    }
}
