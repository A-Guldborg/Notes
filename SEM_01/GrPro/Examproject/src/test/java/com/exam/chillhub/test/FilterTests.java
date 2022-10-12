package com.exam.chillhub.test;

import com.exam.chillhub.enums.MediaType;
import com.exam.chillhub.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterTests {
    private Filter filter;

    @BeforeEach
    public void setUp() {
        // Start filter med 5 forskellige medier, både film og serier
        filter = new Filter("TestFilter");
        for (int i = 1; i <= 5; i++) {
            Media m;
            if (i % 2 == 1) {
                m = new Movie("Test" + i, "2018", 3.3, false, 0);
            } else {
                m = new Series("Test" + i, "2018", 3.3, false, 0);
            }
            filter.addToFilter(m);
        }
    }

    @Test
    public void testAddToFilter() {
        Movie mov = new Movie("Test123", "2018", 3.3, false, 0);
        filter.addToFilter(mov);
        assertTrue(filter.getFilteredData().contains(mov));
    }

    @Test
    public void testFilteredByType_Movie() {
        List<Media> filteredList = filter.getFilteredType(MediaType.MOVIE).getFilteredData();
        assertTrue(filteredList.size() > 0);
        for (Media m : filteredList) {
            assertEquals(m.getType(), MediaType.MOVIE);
        }
    }

    @Test
    public void testFilteredByType_Series() {
        List<Media> filteredList = filter.getFilteredType(MediaType.SERIES).getFilteredData();
        assertTrue(filteredList.size() > 0);
        for (Media m : filteredList) {
            assertEquals(m.getType(), MediaType.SERIES);
        }
    }
}
