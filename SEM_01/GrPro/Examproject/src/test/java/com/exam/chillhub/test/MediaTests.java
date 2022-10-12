package com.exam.chillhub.test;

import com.exam.chillhub.models.Media;
import com.exam.chillhub.enums.MediaType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MediaTests {

    @Test
    public void ctor() {
        var media = new ConcreteMedia(MediaType.MOVIE, "ET", "2012", 1, false, 0);
        assertEquals(media.getType(), MediaType.MOVIE);
        assertEquals(media.getName(), "ET");
        assertEquals(media.getYear(), "2012");
        assertEquals(media.getRating(), 1);
        assertEquals(media.getFavorite(), false);
        assertEquals(media.getIdx(), 0);
    }

    @Test
    void posterProperty() {
        var media = new ConcreteMedia(MediaType.MOVIE, "ET", "2012", 1, false, 0);
        var image = media.posterProperty().get();
        if (image == null)
            fail("Image is null");
    }

    @Test
    void posterProperty_fail() {
        var media = new ConcreteMedia(MediaType.MOVIE, "Doesn't exist", "2012", 1, false, 0);
        var image = media.posterProperty().get();
        if (image != null)
            fail("Image is not null");
    }

    class ConcreteMedia extends Media {
        public ConcreteMedia(MediaType type, String name, String year, double rating, boolean favorite, int idx) {
            super(type, name, year, rating, favorite, idx);
        }
    }
}
