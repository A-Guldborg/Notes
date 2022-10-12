package com.exam.chillhub.test;

import com.exam.chillhub.enums.MediaType;
import com.exam.chillhub.models.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    public void ctor() {
        var media = new Movie("ET", "2012", 1, false, 0);
        assertEquals(media.getType(), MediaType.MOVIE);
        assertEquals(media.getName(), "ET");
        assertEquals(media.getYear(), "2012");
        assertEquals(media.getRating(), 1);
        assertEquals(media.getFavorite(), false);
        assertEquals(media.getIdx(), 0);
    }
}
