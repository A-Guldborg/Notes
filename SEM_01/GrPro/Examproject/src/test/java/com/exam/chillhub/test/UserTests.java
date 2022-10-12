package com.exam.chillhub.test;

import com.exam.chillhub.models.Movie;
import com.exam.chillhub.models.Series;
import com.exam.chillhub.models.User;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTests {
    @Test
    public void ctorName() {
        var user = new User("Test");
        assertEquals(user.getName(), "Test");
        assertEquals(user.getFavorites().getFilteredData().size(), 0);
    }

    @Test
    public void ctorNameColor() {
        var user = new User("Test", "red");
        assertEquals(user.getName(), "Test");
        assertEquals(user.getColorString(), "#6d8c82, #a03f74");
        assertEquals(user.getFavorites().getFilteredData().size(), 0);
    }

    @Test
    public void favorites() {
        var movie = new Movie("ET", "2012", 1, false, 0);
        var series = new Series("24", "2012", 1, false, 0);
        var user = new User("Test");
        assertEquals(user.getFavorites().getFilteredData().size(), 0);
        user.changeFavorite(movie);
        assertEquals(user.getFavorites().getFilteredData().size(), 1);
        user.changeFavorite(series);
        assertEquals(user.getFavorites().getFilteredData().size(), 2);
        user.changeFavorite(series);
        assertEquals(user.getFavorites().getFilteredData().size(), 1);
    }

    @Test
    public void randomColor() {
        var user = new User("Test");
        var orig = user.getColor();
        user.generateColor();
        // 1 in 255^3 chance of failing
        assertNotEquals(user.getColor(), orig);
    }

    @Test
    public void setName() {
        var user = new User("Test");
        user.setName("New");
        assertEquals(user.getName(), "New");
    }
}
